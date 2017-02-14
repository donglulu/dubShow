package open.com.nicesound.view.home;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import open.com.nicesound.R;
import open.com.nicesound.base.BaseFragment;
import open.com.nicesound.bean.HomeHotBean;
import open.com.nicesound.model.HomeHotModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeHotFragment extends BaseFragment {

    private HomeHotModel homeHotModel;

    private GridView gv_hot;

    private List<HomeHotBean.DataBean.FilmBean> filmBeen = new ArrayList<>();

    public HomeHotFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.home_hot_fragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        gv_hot = (GridView) view.findViewById(R.id.gv_hot);
        homeHotModel = HomeHotModel.getInstance();
        homeHotModel.getHotData(new HomeHotModel.HomeHotCallback() {
            @Override
            public void finish(int resultcode, List<HomeHotBean.DataBean.FilmBean> list) {
                filmBeen = list;

                gv_hot.setAdapter(new hotAdapter());
            }
        });

    }



    class hotAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return filmBeen.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getActivity(), R.layout.test_layout, null);
            TextView tv_text = (TextView) view.findViewById(R.id.tv_test);
            tv_text.setText(filmBeen.get(position).getTitle());
            ImageView iv_test = (ImageView) view.findViewById(R.id.iv_test);
            Glide.with(getActivity())
                    .load(filmBeen.get(position).getImg_url())
                    .into(iv_test);
            return view;
        }
    }


    public static HomeHotFragment newInstance() {

        HomeHotFragment fragment = new HomeHotFragment();

        return fragment;
    }

}
