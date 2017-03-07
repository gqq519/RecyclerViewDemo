package com.feicuiedu.recyclerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnList, R.id.btnGrid, R.id.btnStagger})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnList:
                startActivity(new Intent(this,LinearActivity.class));
                break;
            case R.id.btnGrid:
                startActivity(new Intent(this,GridActivity.class));
                break;
            case R.id.btnStagger:
                startActivity(new Intent(this,StaggerActivity.class));
                break;
        }
    }
}
