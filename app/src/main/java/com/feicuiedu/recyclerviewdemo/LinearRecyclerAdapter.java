package com.feicuiedu.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gqq on 2017/3/7.
 */

public class LinearRecyclerAdapter extends RecyclerView.Adapter<LinearRecyclerAdapter.ViewHolder>{

    private List<String> mData = new ArrayList<>();

    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;

    // 设置数据
    public void setData(List<String> data){
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void ItemMoved(int fromPosition, int toPosition,List<String> data) {

        mData.clear();
        mData.addAll(data);
        notifyItemMoved(fromPosition,toPosition);
    }

    // 删除数据
    public void removeData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public void addData(int position){
        mData.add(position,"insert ok");
        notifyItemInserted(position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener longClickListener) {
        mLongClickListener = longClickListener;
    }

    // 创建ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

//        int height = (int) ((Math.random()*100)+100);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height);
//        holder.mTvItem.setLayoutParams(layoutParams);

        holder.mTvItem.setText(mData.get(position));

        if (mClickListener!=null) {
            // 设置点击监听
            holder.mTvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                Toast.makeText(v.getContext(), "点击了item"+position, Toast.LENGTH_SHORT).show();
                    // 具体做什么操作并不知道或者不方便处理，那就让Activity或Fragment处理
                    mClickListener.onItemClick(position);
                }
            });
        }

        if (mLongClickListener!=null) {
            holder.mTvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLongClickListener.onItemLongClick(position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_text)
        TextView mTvItem;

        public ViewHolder(View itemView) {
            super(itemView);
            // 两个参数的！！
            ButterKnife.bind(this,itemView);
        }
    }

    // 点击监听
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // 长按监听
    public interface OnItemLongClickListener{
        void onItemLongClick(int position);
    }
}
