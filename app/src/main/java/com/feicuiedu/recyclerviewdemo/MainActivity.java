package com.feicuiedu.recyclerviewdemo;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.linearView)
    RecyclerView mRecyclerView;
    private List<String> mData;
    private MyRecyclerAdapter mRecyclerAdapter;

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
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 网格
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));

        // 瀑布流:设置水平和垂直
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));



        // 设置item的添加和删除的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置分割线,默认提供一个，我们可以自定义，也可以直接在item布局中画一个分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // 设置适配器
        mRecyclerAdapter = new MyRecyclerAdapter();
        mRecyclerView.setAdapter(mRecyclerAdapter);


        // item的点击监听
        mRecyclerAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "click："+position, Toast.LENGTH_SHORT).show();
            }
        });

        // item长按监听
        mRecyclerAdapter.setOnItemLongClickListener(new MyRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                Toast.makeText(MainActivity.this, "long click:"+position, Toast.LENGTH_SHORT).show();
                mRecyclerAdapter.removeData(position);
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
