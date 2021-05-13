package com.zhaoxuan.myandroidtraining.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhaoxuan.myandroidtraining.R;

/**
 * author : zhaoxuan
 * date : 2021/4/28
 * desc :
 */
public class RecyclerViewItemDecorationUsingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view_item_decoration_using);

        findViewById(R.id.btn_vertical).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewItemDecorationLinearLayoutVerticalActivity
                        .actionStart(RecyclerViewItemDecorationUsingActivity.this);
            }
        });

        findViewById(R.id.btn_horizontal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewItemDecorationLinearLayoutHorizontalActivity
                        .actionStart(RecyclerViewItemDecorationUsingActivity.this);
            }
        });

        findViewById(R.id.btn_grid_vertical).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewItemDecorationGridVerticalActivity
                        .actionStart(RecyclerViewItemDecorationUsingActivity.this);
            }
        });

        findViewById(R.id.btn_grid_horizontal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewItemDecorationGridHorizontalActivity
                        .actionStart(RecyclerViewItemDecorationUsingActivity.this);
            }
        });

        findViewById(R.id.btn_stagger_grid_vertical).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewItemDecorationStaggeredGridVerticalActivity
                        .actionStart(RecyclerViewItemDecorationUsingActivity.this);
            }
        });

        findViewById(R.id.btn_stagger_grid_horizontal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewItemDecorationStaggeredGridHorizontalActivity
                        .actionStart(RecyclerViewItemDecorationUsingActivity.this);
            }
        });
    }


}
