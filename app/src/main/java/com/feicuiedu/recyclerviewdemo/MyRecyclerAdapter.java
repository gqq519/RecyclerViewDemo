package com.feicuiedu.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gqq on 2017/3/7.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>{

    private List<String> mData = new ArrayList<>();

    public void setData(List<String> data){
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    // 创建ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("TAG","textView:"+(holder.mTvItem==null)+",mData:"+(mData==null));
        int height = (int) ((Math.random()*100)+100);
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,height);
        holder.mTvItem.setLayoutParams(layoutParams);
        Log.i("TAG","tv:width:"+holder.mTvItem.getWidth()+",height:"+holder.mTvItem.getHeight());
        holder.mTvItem.setText(mData.get(position));
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

}
