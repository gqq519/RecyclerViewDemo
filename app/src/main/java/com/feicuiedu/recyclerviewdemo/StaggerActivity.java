package com.feicuiedu.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StaggerActivity extends AppCompatActivity {

    @BindView(R.id.linearView)
    RecyclerView mRecyclerView;
    private List<String> mData;

    private MyRecyclerAdapter mRecyclerAdapter;
    private List<Integer> mHeights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stagger);
        ButterKnife.bind(this);

        initView();

        initData();

    }

    private void initView() {

        // 瀑布流:设置水平和垂直
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL));

        // 设置item的添加和删除的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置分割线,默认提供一个，我们可以自定义，也可以直接在item布局中画一个分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // 设置瀑布流的适配器
        mRecyclerAdapter = new MyRecyclerAdapter();
        mRecyclerView.setAdapter(mRecyclerAdapter);

        // item的点击监听
        mRecyclerAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(StaggerActivity.this, "click："+position, Toast.LENGTH_SHORT).show();
            }
        });

        // item长按监听
        mRecyclerAdapter.setOnItemLongClickListener(new MyRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                Toast.makeText(StaggerActivity.this, "long click:"+position, Toast.LENGTH_SHORT).show();
                mRecyclerAdapter.removeData(position);
            }
        });

    }

    private void initData() {
        mData = new ArrayList<>();
        mHeights = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mData.add("" + (char) i);
            mHeights.add((int)(200+Math.random()*400));
        }
        mRecyclerAdapter.setData(mData,mHeights);
    }

    @OnClick(R.id.btnAdd)
    public void onClick() {
        mRecyclerAdapter.addData();
    }
}
