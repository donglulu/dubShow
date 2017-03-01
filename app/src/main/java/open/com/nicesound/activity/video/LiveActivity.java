package open.com.nicesound.activity.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import open.com.nicesound.R;

/**
 * 作者：huangliguang on 2017/2/15 20:18
 * 邮箱：1720189720@qq.com
 */
public class LiveActivity extends AppCompatActivity {
    private WebView mWebView;
    private String share_url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        mWebView = (WebView) findViewById(R.id.mv_live);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String share_url = bundle.getString("share_url");
        Toast.makeText(this,"直播路径"+share_url,Toast.LENGTH_LONG).show();
        System.out.println(share_url);
        Log.d("LiveActivity",share_url);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(share_url);
    }
}
