package open.com.nicesound.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import open.com.nicesound.R;
import open.com.nicesound.base.BaseActivity;

/**  视频详情页面 */
public class VideoActivity extends AppCompatActivity {
    private Toolbar toolbar;
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

    }
}
