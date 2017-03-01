package open.com.nicesound.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import open.com.nicesound.R;
import open.com.nicesound.base.BaseFragment;

/**
 * Created by Crcker on 14/02/2017.
 * 邮箱：635281462@qq.com
 * 我的
 */

public class MineFragment extends BaseFragment {
    private View viewRoot;
    private  View headView;
    private ListView mineList;
    private String name[] = {"新手区","消息中心","草稿箱","已配","自制素材",
            "我的财富","我的钱包","我的成就","我的社团",
            "找好友","关注","粉丝",
            "设置"};
    private int icon[] = {R.drawable.space_icon_novice,R.drawable.ds_space_icon_xiaoxi,R.drawable.ds_space_icon_zuanshi,
            R.drawable.ds_space_icon_material,R.drawable.ds_space_icon_yishangchuan,
            R.drawable.ds_space_icon_money,R.drawable.space_icon_money,
            R.drawable.space_icon_achievement,R.drawable.space_icon_corporate,
            R.drawable.space_icon_addfriend,R.drawable.space_icon_novice,R.drawable.space_icon_fans,R.drawable.space_icon_set};
    private ArrayList<View> itemViewList = new ArrayList<View>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.mine_fragment, container, false);
        mineList = (ListView) viewRoot.findViewById(R.id.lv_mine);
        headView = View.inflate(getContext(),R.layout.mine_item_head,null);
        mineList.addHeaderView(headView);
        return viewRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();

        mineList.setAdapter(new MyMineAdapter());

        mineList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              if (position>0){
                  Toast.makeText(getContext(),"点击位置"+position,Toast.LENGTH_SHORT).show();
              }
            }
        });
    }

    private void initData() {


        headViewClick(headView);
        //点击头部处理

        itemViewList.add(headView);
        for(int i=0;i<name.length;i++){
            View view = View.inflate(getContext(),R.layout.mine_item,null);
            ImageView iconIMG = (ImageView) view.findViewById(R.id.iv_icon);
            TextView tvName = (TextView) view.findViewById(R.id.tv_name);
            iconIMG.setImageResource(icon[i]);
            tvName.setText(name[i]);
            itemViewList.add(view);
        }
    }

    private void headViewClick(View headView) {
        ImageView userIMG = (ImageView) headView.findViewById(R.id.iv_userimg);
        ImageView starIMG = (ImageView) headView.findViewById(R.id.iv_star);
        TextView userName = (TextView) headView.findViewById(R.id.tv_username);

        starIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击位置star",Toast.LENGTH_SHORT).show();
            }
        });
        userIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击位置头像",Toast.LENGTH_SHORT).show();
            }
        });
    }


    class MyMineAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return name.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return itemViewList.get(position+1);
        }
    }

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }
}
