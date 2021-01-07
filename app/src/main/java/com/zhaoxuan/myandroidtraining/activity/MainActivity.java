package com.zhaoxuan.myandroidtraining.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhaoxuan.myandroidtraining.R;

/**
 * author : zhaoxuan
 * date : 2021/1/7
 * desc :
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.skeleton_screen).setOnClickListener(this);
        findViewById(R.id.view_replacer).setOnClickListener(this);
        findViewById(R.id.constraint_layout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.skeleton_screen) {
            Intent intent = new Intent(this, ShimmerSkeletonUsingActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.view_replacer) {
            Intent intent = new Intent(this, ViewReplacerUsingActivity.class);
            startActivity(intent);
        }else if (v.getId() == R.id.constraint_layout) {
            Intent intent = new Intent(this, ConstraintLayoutUsingActivity.class);
            startActivity(intent);
        }
    }
}
