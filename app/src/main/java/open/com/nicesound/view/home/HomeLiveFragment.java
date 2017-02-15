package open.com.nicesound.view.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import open.com.nicesound.R;
import open.com.nicesound.base.BaseFragment;
import open.com.nicesound.bean.HomeLiveBean;
import open.com.nicesound.model.HomeLiveModel;

/**
 * A placeholder fragment containing a simple view.
 * 直播HomeLiveFragment
 * 直播 get
 http://api1.peiyinxiu.com/Api/live/LiveList?appkey=8b232b5997100d5a&pg=1&token=&userId=0&sign=3acd5155480082729eec9c94ccb20049
 直播的上拉刷新
 http://api1.peiyinxiu.com/Api/live/LiveList?appkey=8b232b5997100d5a&pg=2&token=&userId=0&sign=9a27bafcdcae8ed66aa79d140e69f2df

 */
public class HomeLiveFragment extends BaseFragment {

    private View mLiveView;
    private ListView mLiveList;
    List<HomeLiveBean.DataBean.ListBean> mList = new ArrayList<HomeLiveBean.DataBean.ListBean>();
    public HomeLiveFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLiveView  = inflater.inflate(R.layout.home_live_fragment, container, false);
        initView();
        return mLiveView;
    }

    private void initView() {
        mLiveList = (ListView) mLiveView.findViewById(R.id.lv_live);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();

    }

    private void initData() {
        HomeLiveModel.getInstance().getLiveData(new HomeLiveModel.HomeLiveCallBack() {
            @Override
            public void finish(int resultcode, ArrayList<HomeLiveBean.DataBean.ListBean> list) {
                mList =list;
                mLiveList.setAdapter(new LiveAdapter());
            }
        });
    }

    class LiveAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getActivity(),R.layout.home_live_item, null);
                holder = new ViewHolder();
                holder.ivLive = (ImageView) convertView.findViewById(R.id.iv_live);
                holder.ivHead = (ImageView) convertView.findViewById(R.id.iv_head);
                holder.tvUsername = (TextView) convertView.findViewById(R.id.tv_username);
                holder.tvCount = (TextView) convertView.findViewById(R.id.tv_count);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvUsername.setText(mList.get(position).getUser_name());
            holder.tvCount.setText(String.valueOf(mList.get(position).getCount()));
            holder.tvTitle.setText(mList.get(position).getTitle());
            Glide.with(getActivity()).load(mList.get(position).getImg_url()).into(holder.ivLive);
            Glide.with(getActivity()).load(mList.get(position).getUser_head()).into(holder.ivHead);

            return convertView;
        }
        class ViewHolder{
            ImageView ivLive;
            ImageView ivHead;
            TextView tvUsername;
            TextView tvCount;
            TextView tvTitle;

        }
    }
    public static HomeLiveFragment newInstance() {
        HomeLiveFragment fragment = new HomeLiveFragment();
        return fragment;
    }
}
