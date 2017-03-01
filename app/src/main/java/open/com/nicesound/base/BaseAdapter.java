package open.com.nicesound.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author: VincenT
 * Date: 2017/3/1 14:38
 * Contact:qq 328551489
 * Purpose:基本适配器
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
    protected List<T> mData;
    protected Context mContext;
    protected String mType = "android";

    public BaseAdapter(List<T> data) {
        this.mData = data;
    }

    /**实现多类别item时用*/
    public BaseAdapter(List<T> data, String type) {
        this.mData = data;
        this.mType = type;
    }

    /**有弹窗时用*/
    public BaseAdapter(List<T> data, Context context) {
        this.mData = data;
        this.mContext = context;
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    public List<T> getData() {
        return mData;
    }

    public void replaceData(List<T> data) {
        this.mData.clear();
        this.mData.addAll(data);
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getItemLayout(), parent, false);
        return createItemView(view);
    }

    protected abstract int getItemLayout();

    protected abstract RecyclerView.ViewHolder createItemView(View view);


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void remove(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    protected OnItemClickListener mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

}
