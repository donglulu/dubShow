package open.com.nicesound.activity.video;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.superplayer.library.SuperPlayer;

import open.com.nicesound.R;

/**  视频详情页面 */
public class VideoActivity extends AppCompatActivity implements SuperPlayer.OnNetChangeListener {
    private Toolbar toolbar;
    private SuperPlayer viewSuperPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        toolbar = (Toolbar) findViewById(R.id.video_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView Ttitle = (TextView) findViewById(R.id.toolbar_title);
        Ttitle.setText("视频详情");
        viewSuperPlayer = (SuperPlayer) findViewById(R.id.view_super_player);

        //测试视频播放是否成功
        String url1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        initPlayer(url1);

    }

    //播放视频
    private void initPlayer(String url) {
        viewSuperPlayer.setNetChangeListener(true)//设置监听手机网络的变化
                .setOnNetChangeListener(this)//实现网络变化的回调
                .play(url);//开始播放视频
        viewSuperPlayer.setScaleType(SuperPlayer.SCALETYPE_FITXY);
        viewSuperPlayer.setPlayerWH(0, viewSuperPlayer.getMeasuredHeight());//设置竖屏的时候屏幕的高度，如果不设置会切换后按照16:9的高度重置
    }

    @Override
    public void onWifi() {
        Toast.makeText(this,"当前网络环境是WIFI",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMobile() {
        Toast.makeText(this, "当前网络环境是手机网络",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisConnect() {
        Toast.makeText(this, "网络链接断开",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoAvailable() {
        Toast.makeText(this, "无网络链接",Toast.LENGTH_SHORT).show();
    }

    /**
     * 下面的这几个Activity的生命状态很重要
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (viewSuperPlayer != null) {
            viewSuperPlayer.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (viewSuperPlayer != null) {
            viewSuperPlayer.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewSuperPlayer != null) {
            viewSuperPlayer.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (viewSuperPlayer != null) {
            viewSuperPlayer.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (viewSuperPlayer != null && viewSuperPlayer.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

}
