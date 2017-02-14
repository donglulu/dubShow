package open.com.nicesound.model;


import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import open.com.nicesound.bean.HomeHotBean;
import open.com.nicesound.constant.Constants;
import open.com.nicesound.httpcallback.HomeHotHttpCallBack;

/**
 * Created by Crcker on 14/02/2017.
 * 邮箱：635281462@qq.com
 * 主页热门的操作类
 */

public class HomeHotModel {

    private static HomeHotModel homeHotModel;

    private HomeHotModel() {
    }


    public static HomeHotModel getInstance() {
        if (homeHotModel == null) {
            homeHotModel = new HomeHotModel();
        }
        return homeHotModel;
    }

    /**
     * 获取热门的json数据
     */

    public void getHotData(final HomeHotCallback callback) {
        OkHttpUtils
                .get()
                .url(Constants.SERVER_ADRESS + Constants.HOME_HOT)
                .build().execute(new HomeHotHttpCallBack() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(ArrayList<HomeHotBean.DataBean.FilmBean> response) {
                callback.finish(Constants.RESULT_SUCCESS,response);
            }
        });


    }

    public interface HomeHotCallback {

        void finish(int resultcode, List<HomeHotBean.DataBean.FilmBean> list);


    }

}
