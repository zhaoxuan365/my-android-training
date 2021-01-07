package com.zhaoxuan.myandroidtraining.activity;

import android.app.Activity;
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
public class ShimmerSkeletonUsingActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shimmer_skeleton_using);

        findViewById(R.id.skeleton_view).setOnClickListener(this);
        findViewById(R.id.skeleton_recycler_view).setOnClickListener(this);
        findViewById(R.id.skeleton_layout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.skeleton_view) {
            Intent intent = new Intent(this, SkeletonViewUsingActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.skeleton_recycler_view) {
            Intent intent = new Intent(this, SkeletonRecyclerViewUsingActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.skeleton_layout) {
            Intent intent = new Intent(this, SkeletonLayoutUsingActivity.class);
            startActivity(intent);
        }
    }
}
