package com.zhaoxuan.myandroidtraining.widget.shimmerskeleton.skeleton;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * author : zhaoxuan
 * date : 2021/1/4
 * desc : https://github.com/ethanhua/Skeleton
 */
public class SkeletonUtils {

    public static SkeletonRecyclerView.Builder bind(RecyclerView recyclerView) {
        return new SkeletonRecyclerView.Builder(recyclerView);
    }

    public static SkeletonView.Builder bind(View view) {
        return new SkeletonView.Builder(view);
    }

}
