package open.com.nicesound.model;

import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;

import okhttp3.Call;
import open.com.nicesound.bean.HomeHotBean;
import open.com.nicesound.bean.HomeLiveBean;
import open.com.nicesound.constant.Constants;
import open.com.nicesound.httpcallback.HomeLiveHttpCallBack;
import open.com.nicesound.presenter.homepresenter.IHomeFragmentPresenter;

/**
 * 作者：huangliguang on 2017/2/15 13:06
 * 邮箱：1720189720@qq.com
 */
public class HomeLiveModel{
    private static HomeLiveModel mHomeLiveModel;
    private ArrayList<HomeLiveBean.DataBean.ListBean> list;
    private HomeLiveModel(){
        list = new ArrayList<HomeLiveBean.DataBean.ListBean>();
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
                //判断是否含有之前的
                list.addAll(response);
                callback.finish(Constants.RESULT_SUCCESS,list);
            }
        });
    }

    public void onRefresh(final HomeLiveRefreshCallBack refreshCallBack){
        OkHttpUtils.get().url(Constants.SERVER_ADRESS+Constants.HOME_LIVE_REFRESH).build().execute(new HomeLiveHttpCallBack() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(ArrayList<HomeLiveBean.DataBean.ListBean> response) {
                for (int i = 0; i <response.size(); i++) {
                    boolean contains = list.contains(response.get(i));
                    if (!contains){
                        list.add(0,response.get(i));
                    }
                }
                refreshCallBack.finish(Constants.RESULT_SUCCESS,list);
            }
        });
    }
    public interface HomeLiveCallBack {
        void finish(int resultcode, ArrayList<HomeLiveBean.DataBean.ListBean> list);
    }
    public interface HomeLiveRefreshCallBack {
        void finish(int resultcode, ArrayList<HomeLiveBean.DataBean.ListBean> list);
    }
}
