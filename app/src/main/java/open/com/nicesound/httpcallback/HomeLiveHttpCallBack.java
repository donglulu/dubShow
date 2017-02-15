package open.com.nicesound.httpcallback;

import android.util.Log;

import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Response;
import open.com.nicesound.bean.HomeLiveBean;

/**
 * 作者：huangliguang on 2017/2/15 15:52
 * 邮箱：1720189720@qq.com
 */
public abstract class HomeLiveHttpCallBack extends Callback<ArrayList<HomeLiveBean.DataBean.ListBean>> {
    HomeLiveBean  homeLiveBean = null;
    ArrayList<HomeLiveBean.DataBean.ListBean> ListBean = null;
    @Override
    public ArrayList<HomeLiveBean.DataBean.ListBean> parseNetworkResponse(Response response) throws Exception {
        String jsonContent = response.body().string();
        Log.d("msg", jsonContent);
        if (jsonContent != null && jsonContent.length() > 0) {
            JSONObject jsonObject = new JSONObject(jsonContent);
            homeLiveBean = new HomeLiveBean();
            ListBean = new ArrayList<HomeLiveBean.DataBean.ListBean>();
            homeLiveBean.setCode(jsonObject.getInt("code"));

            JSONArray array = jsonObject.getJSONObject("data").getJSONArray("list");

            HomeLiveBean.DataBean.ListBean mBean;


            for (int i = 0; i < array.length(); i++) {
                mBean = new  HomeLiveBean.DataBean.ListBean();

                mBean.setImg_url(array.getJSONObject(i).getString("img_url"));
                mBean.setUser_name(array.getJSONObject(i).getString("user_name"));
                mBean.setUser_head(array.getJSONObject(i).getString("user_head"));
                mBean.setTitle(array.getJSONObject(i).getString("title"));
                mBean.setCount(array.getJSONObject(i).getInt("count"));
                ListBean.add(mBean);
                mBean = null;
            }
        }
        return ListBean;
    }
}
