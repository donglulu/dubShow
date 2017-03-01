
package open.com.nicesound.view;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.util.IOUtils;
import master.flame.danmaku.danmaku.util.SystemClock;
import open.com.nicesound.R;
import open.com.nicesound.custom.BackgroundCacheStuffer;
import open.com.nicesound.parser.DanmakuParser;
import open.com.nicesound.utils.TextUtils;

/**
 * Author: VincenT
 * Date: 2017/2/28 10:34
 * Contact:qq 328551489
 * Purpose:此类用于测试弹幕，未在项目接入入口
 */
public class TextOnlyActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.rotate)
    Button mBtnRotate;
    @BindView(R.id.btn_hide)
    Button mBtnHideDanmaku;
    @BindView(R.id.btn_show)
    Button mBtnShowDanmaku;
    @BindView(R.id.btn_pause)
    Button mBtnPauseDanmaku;
    @BindView(R.id.btn_resume)
    Button mBtnResumeDanmaku;
    @BindView(R.id.btn_send)
    Button mBtnSendDanmakuTextOnly;
    @BindView(R.id.btn_send_image_text)
    Button mBtnSendDanmakuTextAndImage;
    @BindView(R.id.btn_send_danmakus)
    Button mBtnSendDanmakus;
    @BindView(R.id.media_controller)
    View mMediaController;
    @BindView(R.id.videoview)
    VideoView mVideoView;
    @BindView(R.id.sv_danmaku)
    IDanmakuView mDanmakuView;


    private DanmakuContext mContext;
    private Timer timer = new Timer();
    private BaseDanmakuParser mParser;
    private HashMap<Integer, Integer> maxLinesPair = new HashMap<>();
    private HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<>();

    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                addDanmaku(true);
                SystemClock.sleep(20);
            }
        }
    };

    private BaseCacheStuffer.Proxy mCacheStufferAdapter = new BaseCacheStuffer.Proxy() {
        private Drawable mDrawable;
        @Override
        public void prepareDrawing(final BaseDanmaku danmaku, boolean fromWorkerThread) {
            Log.d("debug", "prepareDrawing");
            if (danmaku.text instanceof Spanned) { // 根据你的条件检查是否需要需要更新弹幕
                Log.d("debug", "danmaku.text = " + danmaku.text);
                // 这里只是简单启个线程来加载远程url图片，请使用你自己的异步线程池，最好加上你的缓存池
                new Thread() {
                    @Override
                    public void run() {
                        String url = "http://www.bilibili.com/favicon.ico";
                        InputStream inputStream = null;
                        Drawable drawable = mDrawable;
                        if (drawable == null) {
                            try {
                                URLConnection urlConnection = new URL(url).openConnection();
                                inputStream = urlConnection.getInputStream();
                                drawable = BitmapDrawable.createFromStream(inputStream, "bitmap");
                                mDrawable = drawable;
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                IOUtils.closeQuietly(inputStream);
                            }
                        }
                        if (drawable != null) {
                            drawable.setBounds(0, 0, 100, 100);
                            danmaku.text = TextUtils.createSpannable(drawable);
                            if (mDanmakuView != null) {
                                mDanmakuView.invalidateDanmaku(danmaku, false);
                            }
                        }
                    }
                }.start();
            }
        }

        @Override
        public void releaseResource(BaseDanmaku danmaku) {
            // 重要:清理含有ImageSpan的tex中的一些占用内存的资源 例如drawable
            Log.d("debug", "releaseResource" + danmaku.text);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 5); // 滚动弹幕最大显示5行
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);// 设置是否禁止重叠
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);
        mContext = DanmakuContext.create();
        mContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3).setDuplicateMergingEnabled(false).setScrollSpeedFactor(1.2f).setScaleTextSize(1.2f)
//            .setCacheStuffer(new SpannedCacheStuffer(), mCacheStufferAdapter) // 图文混排使用SpannedCacheStuffer
            .setCacheStuffer(new BackgroundCacheStuffer(), mCacheStufferAdapter)  // 绘制背景使用BackgroundCacheStuffer
            .setMaximumLines(maxLinesPair)
            .preventOverlapping(overlappingEnablePair);
        if (mDanmakuView != null) {
            mParser = DanmakuParser.createParser(this.getResources().openRawResource(R.raw.comments));
            mDanmakuView.setCallback(new master.flame.danmaku.controller.DrawHandler.Callback() {
                @Override
                public void updateTimer(DanmakuTimer timer) {
                }

                @Override
                public void drawingFinished() {

                }

                @Override
                public void danmakuShown(BaseDanmaku danmaku) {
//                    Log.d("DFM", "danmakuShown(): text=" + danmaku.text);
                }

                @Override
                public void prepared() {
                    mDanmakuView.start();
                }
            });
            mDanmakuView.setOnDanmakuClickListener(new IDanmakuView.OnDanmakuClickListener() {
                @Override
                public boolean onDanmakuClick(IDanmakus danmakus) {
                    Log.d("DFM", "onDanmakuClick: danmakus size:" + danmakus.size());
                    BaseDanmaku latest = danmakus.last();
                    if (null != latest) {
                        Log.d("DFM", "onDanmakuClick: text of latest danmaku:" + latest.text);
                        return true;
                    }
                    return false;
                }

                @Override
                public boolean onViewClick(IDanmakuView view) {
                    mMediaController.setVisibility(View.VISIBLE);
                    return false;
                }
            });
            mDanmakuView.prepare(mParser, mContext);
            mDanmakuView.showFPS(true);
            mDanmakuView.enableDanmakuDrawingCache(true);
        }
        if (mVideoView != null) {
            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
            String path = Environment.getExternalStorageDirectory() + "/1.flv";
            mVideoView.setVideoPath(path);
        }

    }

    @OnClick({R.id.rotate, R.id.btn_hide, R.id.btn_show, R.id.btn_pause, R.id.btn_resume, R.id.btn_send, R.id.btn_send_image_text, R.id.btn_send_danmakus, R.id.media_controller})
    public void onClick(View view) {
        if (view == mMediaController) {
            mMediaController.setVisibility(View.GONE);
        }
        if (mDanmakuView == null || !mDanmakuView.isPrepared())
            return;
        if (view == mBtnRotate) {//旋转屏幕
            setRequestedOrientation(getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT : ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else if (view == mBtnHideDanmaku) {
            mDanmakuView.hide();
            // mPausedPosition = mDanmakuView.hideAndPauseDrawTask();
        } else if (view == mBtnShowDanmaku) {
            mDanmakuView.show();
            // mDanmakuView.showAndResumeDrawTask(mPausedPosition); // sync to the video time in your practice
        } else if (view == mBtnPauseDanmaku) {
            mDanmakuView.pause();
        } else if (view == mBtnResumeDanmaku) {
            mDanmakuView.resume();
        } else if (view == mBtnSendDanmakuTextOnly) {
            addDanmaku(false);
        } else if (view == mBtnSendDanmakuTextAndImage) {
            addDanmakuShowTextAndImage(false);
        } else if (view == mBtnSendDanmakus) {
            Boolean b = (Boolean) mBtnSendDanmakus.getTag();
            timer.cancel();
            if (b == null || !b) {
                mBtnSendDanmakus.setText(R.string.danmaku_timing_cancel);
                timer = new Timer();
                timer.schedule(mTimerTask, 0, 1000);
                mBtnSendDanmakus.setTag(true);
            } else {
                mBtnSendDanmakus.setText(R.string.danmaku_timing_send);
                mBtnSendDanmakus.setTag(false);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mDanmakuView != null) {
            mDanmakuView.release();
            mDanmakuView = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            mDanmakuView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDanmakuView != null) {
            mDanmakuView.release();
            mDanmakuView = null;
        }
    }

    /**
     * 纯文字弹幕
     * @param isLive
     */
    private void addDanmaku(boolean isLive) {
        BaseDanmaku danmaku = mContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        if (danmaku == null || mDanmakuView == null) {
            return;
        }
        danmaku.padding = 5;
        danmaku.priority = 0;  //弹幕优先级,0为低优先级,>0为高优先级不会被过滤器过滤
        danmaku.isLive = isLive;
        danmaku.text = "这是一条弹幕" + System.nanoTime();
        danmaku.setTime(mDanmakuView.getCurrentTime() + 1200);
        danmaku.textSize = 25f * (mParser.getDisplayer().getDensity() - 0.6f);
        danmaku.textColor = Color.RED;
        danmaku.textShadowColor = Color.WHITE;
//        danmaku.underlineColor = Color.GREEN;
        danmaku.borderColor = Color.GREEN;
        mDanmakuView.addDanmaku(danmaku);

    }

    /**
     * 文字附带图片弹幕
     * @param isLive
     */
    private void addDanmakuShowTextAndImage(boolean isLive) {
        BaseDanmaku danmaku = mContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        if (danmaku == null || mDanmakuView == null) {
            return;
        }
        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);//用户头像
        drawable.setBounds(0, 0, 100, 100);
        danmaku.setTime(mDanmakuView.getCurrentTime() + 1200);
        danmaku.padding = 5;
        danmaku.priority = 1;  //弹幕优先级,0为低优先级,>0为高优先级不会被过滤器过滤
        danmaku.isLive = isLive;
        danmaku.text = TextUtils.createSpannable(drawable);
        danmaku.textSize = 25f * (mParser.getDisplayer().getDensity() - 0.6f);
        danmaku.textColor = Color.RED;
        danmaku.textShadowColor = 0; // 重要：如果有图文混排，最好不要设置描边(设textShadowColor=0)，否则会进行两次复杂的绘制导致运行效率降低
        danmaku.underlineColor = Color.GREEN;
//        danmaku.borderColor = Color.GREEN;
        mDanmakuView.addDanmaku(danmaku);
    }

}
