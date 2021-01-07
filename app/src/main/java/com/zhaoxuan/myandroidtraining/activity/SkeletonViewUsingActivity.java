package com.zhaoxuan.myandroidtraining.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhaoxuan.myandroidtraining.R;
import com.zhaoxuan.shimmerskeleton.skeleton.SkeletonUtils;
import com.zhaoxuan.shimmerskeleton.skeleton.SkeletonView;


/**
 * author : zhaoxuan
 * date : 2021/1/7
 * desc :
 */
public class SkeletonViewUsingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_skeleton_view_using);

        View rootView = findViewById(R.id.root_view);

        SkeletonView skeletonView = SkeletonUtils.bind(rootView)
                .load(R.layout.activity_skeleton_view_loading)
//                .shimmer(new Shimmer.ColorHighlightBuilder()
//                        .setBaseColor(ContextCompat.getColor(this, R.color.design_default_color_secondary))
//                        .setHighlightColor(ContextCompat.getColor(this, R.color.design_default_color_error))
//                        .build())
                .show();

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                skeletonView.hide();
            }
        }, 5000);
    }
}
