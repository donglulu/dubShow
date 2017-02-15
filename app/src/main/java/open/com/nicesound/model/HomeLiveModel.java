package open.com.nicesound.model;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;

import okhttp3.Call;
import open.com.nicesound.bean.HomeHotBean;
import open.com.nicesound.bean.HomeLiveBean;
import open.com.nicesound.constant.Constants;
import open.com.nicesound.httpcallback.HomeLiveHttpCallBack;

/**
 * 作者：huangliguang on 2017/2/15 13:06
 * 邮箱：1720189720@qq.com
 */
public class HomeLiveModel {
    private static HomeLiveModel mHomeLiveModel;
    private ArrayList<HomeHotBean.DataBean.FilmBean> list;
    private HomeLiveModel(){

        list = new ArrayList<HomeHotBean.DataBean.FilmBean>();
    }


    public static HomeLiveModel getInstance() {
        if (mHomeLiveModel==null){
            mHomeLiveModel = new HomeLiveModel();
        }
        return mHomeLiveModel;
    }
    public void getLiveData(final HomeLiveCallBack callback){
        OkHttpUtils.get().url(Constants.SERVER_ADRESS+Constants.HOME_LIVE).build().execute(new HomeLiveHttpCallBack() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(ArrayList<HomeLiveBean.DataBean.ListBean> response) {
                callback.finish(Constants.RESULT_SUCCESS,response);
            }
        });
    }

    public interface HomeLiveCallBack {
        void finish(int resultcode, ArrayList<HomeLiveBean.DataBean.ListBean> list);
    }
}
