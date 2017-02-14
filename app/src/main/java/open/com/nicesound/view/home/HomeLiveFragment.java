package open.com.nicesound.view.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import open.com.nicesound.R;
import open.com.nicesound.base.BaseFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeLiveFragment extends BaseFragment {

    public HomeLiveFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_live_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }


    public static HomeLiveFragment newInstance() {

        HomeLiveFragment fragment = new HomeLiveFragment();

        return fragment;
    }
}
