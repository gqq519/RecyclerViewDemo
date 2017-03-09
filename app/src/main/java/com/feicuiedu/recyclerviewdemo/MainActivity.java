package com.feicuiedu.recyclerviewdemo;

import android.content.ClipData;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.linearView)
    RecyclerView mRecyclerView;
    private List<String> mData;
    private LinearRecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {

        /**
         * 设置RecyclerView的展示样式:提供三种视图
         * 1. LinearLayoutManager
         * 2. GridLayoutManager
         * 3. StaggeredGridLayoutManager
         */

        // 线性：类似ListView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 网格
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));


        // 设置item的添加和删除的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置分割线,默认提供一个，我们可以自定义，也可以直接在item布局中画一个分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        // 设置线性等适配器
        mRecyclerAdapter = new LinearRecyclerAdapter();
        mRecyclerView.setAdapter(mRecyclerAdapter);

        /**
         1、dragDirs - 表示拖拽的方向，有六个类型的值：LEFT、RIGHT、START、END、UP、DOWN
         2、swipeDirs - 表示滑动的方向，有六个类型的值：LEFT、RIGHT、START、END、UP、DOWN
         【注】：如果为0，则表示不触发该操作（滑动or拖拽）
         */
        ItemTouchHelper.Callback callback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT, ItemTouchHelper.RIGHT) {

            /**
             * 拖动
             * @param recyclerView
             * @param viewHolder 拖动的ViewHolder
             * @param target 目标的ViewHolder
             * @return
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
                int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
                if (fromPosition < toPosition) {
                    //分别把中间所有的item的位置重新交换
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(mData, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(mData, i, i - 1);
                    }
                }
//                mRecyclerAdapter.setData(mData);
                mRecyclerAdapter.ItemMoved(fromPosition,toPosition,mData);
                //返回true表示执行拖动
                return true;
            }

            /**
             * 滑动
             *
             * @param viewHolder
             * @param direction
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mRecyclerAdapter.removeData(position);

            }
        };

        // 实现拖拽和侧滑
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);


        // item的点击监听
        mRecyclerAdapter.setOnItemClickListener(new LinearRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "click："+position, Toast.LENGTH_SHORT).show();
            }
        });

        // item长按监听
        mRecyclerAdapter.setOnItemLongClickListener(new LinearRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                Toast.makeText(MainActivity.this, "long click:"+position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick(R.id.btnAdd)
    public void addItem(View view){
        mRecyclerAdapter.addData(1);
    }

    // 设置数据
    private void initData() {
        mData = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mData.add("" + (char) i);
        }
        mRecyclerAdapter.setData(mData);
    }
}
