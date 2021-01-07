package com.zhaoxuan.myandroidtraining.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhaoxuan.myandroidtraining.R;
import com.zhaoxuan.myandroidtraining.adapter.NewsAdapter;
import com.zhaoxuan.myandroidtraining.widget.shimmerskeleton.skeleton.SkeletonRecyclerView;
import com.zhaoxuan.myandroidtraining.widget.shimmerskeleton.skeleton.SkeletonUtils;

/**
 * author : zhaoxuan
 * date : 2021/1/7
 * desc :
 */
public class SkeletonRecyclerViewUsingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_skeleton_recycler_view_using);

        init();
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        NewsAdapter adapter = new NewsAdapter();
        SkeletonRecyclerView skeletonRecyclerView = SkeletonUtils.bind(recyclerView)
                .adapter(adapter)
//                .count(20)
//                .frozen(false)
//                .shimmer(new Shimmer.AlphaHighlightBuilder()
//                        .setDirection(Shimmer.Direction.RIGHT_TO_LEFT)
//                        .build())
                .load(R.layout.item_news_loading)
                .show();

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                skeletonRecyclerView.hide();
            }
        }, 5000);
    }
}
