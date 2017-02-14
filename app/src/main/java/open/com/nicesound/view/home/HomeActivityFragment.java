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
public class HomeActivityFragment extends BaseFragment {

    public HomeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_activity_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    public static HomeActivityFragment newInstance() {

        HomeActivityFragment fragment = new HomeActivityFragment();

        return fragment;
    }
}
