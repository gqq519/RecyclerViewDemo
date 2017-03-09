package com.feicuiedu.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gqq on 2017/3/7.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>{

    private List<String> mData = new ArrayList<>();
    private List<Integer> mHeights = new ArrayList<>();

    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;

//    // 获取item的随机高度：也可以在onBindViewHolder里面设置
//    public void getRandomHeight(List<String> lists){
//        heights = new ArrayList<>();
//        for (int i = 0; i < lists.size(); i++) {
//            heights.add((int)(200+Math.random()*400));
//        }
//    }

    // 设置数据
    public void setData(List<String> data,List<Integer> heights){

        mHeights.clear();
        mHeights.addAll(heights);
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    // 删除一条数据
    public void removeData(int position) {
        mData.remove(position);
        mHeights.remove(position);
        notifyItemRemoved(position);
//        notifyDataSetChanged();
        notifyItemRangeChanged(position,mData.size()-position);
    }

    // 加一条数据
    public void addData(){
        mData.add("insert data");
        mHeights.add((int) (200+Math.random()*400));
//        notifyItemInserted(position);
        notifyDataSetChanged();
//        notifyItemRangeChanged(position,mData.size()-position);

//        notifyItemChanged(int position);
//        更新列表position位置上的数据可以调用
//        notifyItemInserted(int position)
//        列表position位置添加一条数据时可以调用，伴有动画效果
//        notifyItemRemoved(int position)
//        列表position位置移除一条数据时调用，伴有动画效果
//        notifyItemMoved(int fromPosition, int toPosition)
//        列表fromPosition位置的数据移到toPosition位置时调用，伴有动画效果
//        notifyItemRangeChanged(int positionStart, int itemCount)
//        列表从positionStart位置到itemCount数量的列表项进行数据刷新
//        notifyItemRangeInserted(int positionStart, int itemCount)
//        列表从positionStart位置到itemCount数量的列表项批量添加数据时调用，伴有动画效果
//        notifyItemRangeRemoved(int positionStart, int itemCount)
//        列表从positionStart位置到itemCount数量的列表项批量删除数据时调用，伴有动画效果

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

        ViewGroup.LayoutParams layoutParams = holder.mTvItem.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.mTvItem.setLayoutParams(layoutParams);

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
