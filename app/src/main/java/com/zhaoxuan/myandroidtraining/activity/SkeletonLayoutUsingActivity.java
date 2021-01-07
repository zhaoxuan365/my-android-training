package com.zhaoxuan.myandroidtraining.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhaoxuan.myandroidtraining.R;
import com.zhaoxuan.myandroidtraining.widget.shimmerskeleton.shimmer.ShimmerFrameLayout;


/**
 * author : zhaoxuan
 * date : 2021/1/7
 * desc :
 */
public class SkeletonLayoutUsingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_skeleton_layout_using);

        ShimmerFrameLayout shimmerLayout = findViewById(R.id.shimmer_layout);
//        shimmerLayout.setShimmer(new Shimmer.ColorHighlightBuilder()
//                .setBaseColor(ContextCompat.getColor(this, R.color.design_default_color_secondary))
//                .setHighlightColor(ContextCompat.getColor(this, R.color.design_default_color_error))
//                .build());
//        shimmerLayout.setShimmer(new Shimmer.AlphaHighlightBuilder()
//                .setBaseAlpha(0.5f)
//                .setHighlightAlpha(1)
//                .build());
        shimmerLayout.showShimmer();
//        shimmerLayout.startShimmer();

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                shimmerLayout.hideShimmer();
//                shimmerLayout.stopShimmer();
            }
        }, 5000);
    }
}
