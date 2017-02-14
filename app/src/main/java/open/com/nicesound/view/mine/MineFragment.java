package open.com.nicesound.view.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import open.com.nicesound.R;
import open.com.nicesound.base.BaseFragment;

/**
 * Created by Crcker on 14/02/2017.
 * 邮箱：635281462@qq.com
 * 我的
 */

public class MineFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mine_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static MineFragment newInstance() {

        MineFragment fragment = new MineFragment();

        return fragment;
    }


}
