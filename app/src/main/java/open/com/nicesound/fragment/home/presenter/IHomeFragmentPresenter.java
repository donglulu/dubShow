package open.com.nicesound.fragment.home.presenter;

import android.content.Context;

/**
 * Created by Crcker on 14/02/2017.
 *
 * 主页的presenter，用于定义方法，由view去实现
 *  主页Fragment+Viewpager
 *  1、数据的获取
 *
 *
 *
 */

public interface IHomeFragmentPresenter {
    void getAllDate(Context context,String url);

}
