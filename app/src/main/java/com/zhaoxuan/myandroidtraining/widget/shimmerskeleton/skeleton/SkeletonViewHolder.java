package com.zhaoxuan.myandroidtraining.widget.shimmerskeleton.skeleton;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.zhaoxuan.myandroidtraining.R;

/**
 * author : zhaoxuan
 * date : 2021/1/4
 * desc : https://github.com/ethanhua/Skeleton
 */
public class SkeletonViewHolder extends RecyclerView.ViewHolder {

    public SkeletonViewHolder(LayoutInflater inflater, ViewGroup parent, int innerViewResId) {

        super(inflater.inflate(R.layout.layout_shimmer, parent, false));
        ViewGroup layout = (ViewGroup) itemView;
        View view = inflater.inflate(innerViewResId, layout, false);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp != null) {
            layout.setLayoutParams(lp);
        }
        layout.addView(view);
    }
}
