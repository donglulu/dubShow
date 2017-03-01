package open.com.nicesound.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import open.com.nicesound.R;
import open.com.nicesound.base.BaseFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeActivityFragment extends BaseFragment {
    private OkHttpClient client = new OkHttpClient();

    private ArrayList<String> url = new ArrayList<>();

    public HomeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_activity_fragment, container, false);
    }

   /* class thread extends Thread {
        @Override
        public void run() {
            try {
                String content = get("http://m.mmjpg.com");
//                int start = s.indexOf("所有");
//                int end = s.indexOf("第四印象");
//                String substring = s.substring(start, end);
//                Log.d("msg", substring);

                indexOF(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
            super.run();
        }
    }*/

    public void indexOF(String content) {


        int start = content.indexOf("http://img");
        int end = content.indexOf(".jpg");

        String substring = content.substring(start, end) + ".jpg";

        url.add(substring);

        String replace = content.replace(substring, "");
        Log.d("msg", substring);
        indexOF(replace);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //new thread().start();

        super.onActivityCreated(savedInstanceState);
    }

    String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    public static HomeActivityFragment newInstance() {

        HomeActivityFragment fragment = new HomeActivityFragment();

        return fragment;
    }
}
