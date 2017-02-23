package open.com.nicesound.view.circle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import open.com.nicesound.R;


public class CircleAdapter extends BaseAdapter {
    private Context context;
    private int resourceId;


    List<Map<String, String>> listData = new ArrayList<Map<String, String>>();



    public CircleAdapter(List<Map<String, String>> listData, Context context,int resourceId) {
        // TODO Auto-generated constructor stub
        super();
        this.resourceId=resourceId;
        this.listData = listData;
        this.context = context;

    }



    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listData.size();
    }


    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }








    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder ;


        if (convertView == null || convertView.getTag() == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(resourceId, null);

            viewHolder = new ViewHolder();

            viewHolder.name=(TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.postNum=(TextView)convertView.findViewById(R.id.tv_post_num);
            viewHolder.attentionNum=(TextView)convertView.findViewById(R.id.tv_attention_num);
            viewHolder.img=(CircleImageView)convertView.findViewById(R.id.img);
            convertView.setTag(viewHolder);



        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }




        viewHolder.name.setText(listData.get(position).get("name"));
        viewHolder.postNum.setText(listData.get(position).get("postNum"));
        viewHolder.attentionNum.setText(listData.get(position).get("attentionNum"));
        //viewHolder.img.setImageResource(R.mipmap.circle);






        return convertView;
    }








    class ViewHolder {
        TextView name,attentionNum,postNum;
        CircleImageView img;

    }




}
