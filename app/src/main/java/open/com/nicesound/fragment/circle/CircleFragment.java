package open.com.nicesound.fragment.circle;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import open.com.nicesound.R;
import open.com.nicesound.base.BaseFragment;

/**
 * Created by Crcker on 14/02/2017.
 * 邮箱：635281462@qq.com
 * 我的
 */

public class CircleFragment extends BaseFragment {


    private Map<String,String> map;
    private List<Map<String,String>> listData;
    private CircleAdapter adapter;
    private View view;
    ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.circle_fragment, container, false);
        initView();

        return view;
    }


    public void initView(){
        TextView textView=(TextView) view.findViewById(R.id.title_tv);
        listView =(ListView) view.findViewById(R.id.lv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),CircleDetailActivity.class);
                startActivity(intent);
            }
        });
        initDate();


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),CircleDetailActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
    public static CircleFragment newInstance() {

        CircleFragment fragment = new CircleFragment();

        return fragment;
    }

    public void initDate(){
        listData=new ArrayList<Map<String,String>>();
        for (int i = 0; i < 5; i++) {
            map = new HashMap<String, String>();
            map.put("name", "name" + i);
            map.put("img", "name" + i);
            map.put("postNum", "postNum" + i);
            map.put("attentionNum", "postNum" + i);
            listData.add(map);
        }
        adapter =new CircleAdapter(listData,getActivity(),R.layout.circle_lv_item);
        listView.setAdapter(adapter);
    }

}
