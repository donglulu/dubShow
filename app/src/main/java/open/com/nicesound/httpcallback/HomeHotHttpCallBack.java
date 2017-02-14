package open.com.nicesound.httpcallback;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;
import open.com.nicesound.bean.HomeHotBean;

/**
 * Created by Crcker on 14/02/2017.
 * 邮箱：635281462@qq.com
 */

public abstract class HomeHotHttpCallBack extends Callback<ArrayList<HomeHotBean.DataBean.FilmBean>> {
    HomeHotBean homeHotBean = null;
    ArrayList<HomeHotBean.DataBean.FilmBean> filmBeens = null;

    @Override
    public ArrayList<HomeHotBean.DataBean.FilmBean> parseNetworkResponse(Response response) throws Exception {

        String jsonContent = response.body().string();
        Log.d("msg", jsonContent);
        if (jsonContent != null && jsonContent.length() > 0) {
            JSONObject jsonObject = new JSONObject(jsonContent);
            homeHotBean = new HomeHotBean();
            filmBeens = new ArrayList<>();
            homeHotBean.setCode(jsonObject.getInt("code"));
            JSONArray array = jsonObject.getJSONObject("data").getJSONArray("film");

            HomeHotBean.DataBean.FilmBean filmBean;


            for (int i = 0; i < array.length(); i++) {
                filmBean = new HomeHotBean.DataBean.FilmBean();

                filmBean.setImg_url(array.getJSONObject(i).getString("img_url"));
                filmBean.setUser_name(array.getJSONObject(i).getString("user_name"));
                filmBean.setUser_head(array.getJSONObject(i).getString("user_head"));
                filmBean.setTitle(array.getJSONObject(i).getString("title"));

                filmBeens.add(filmBean);
                filmBean = null;

            }
        }

        return filmBeens;
    }


}
