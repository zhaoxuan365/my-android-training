package com.zhaoxuan.myandroidtraining.widget.shimmerskeleton.skeleton;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhaoxuan.myandroidtraining.widget.shimmerskeleton.shimmer.Shimmer;
import com.zhaoxuan.myandroidtraining.widget.shimmerskeleton.shimmer.ShimmerFrameLayout;

/**
 * author : zhaoxuan
 * date : 2021/1/4
 * desc : https://github.com/ethanhua/Skeleton
 */
public class SkeletonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int mItemCount;
    private int mLayoutReference;
    private int[] mLayoutArrayReferences;
    private Shimmer mShimmer;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (doesArrayOfLayoutsExist()) {
            mLayoutReference = viewType;
        }
        return new SkeletonViewHolder(inflater, parent, mLayoutReference);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ShimmerFrameLayout layout = (ShimmerFrameLayout) holder.itemView;
        if (mShimmer != null) {
            layout.setShimmer(mShimmer);
        }
        layout.startShimmer();
    }

    @Override
    public int getItemViewType(int position) {
        if (doesArrayOfLayoutsExist()) {
            return getCorrectLayoutItem(position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    public void setLayoutReference(int layoutReference) {
        this.mLayoutReference = layoutReference;
    }

    public void setArrayOfLayoutReferences(int[] layoutReferences) {
        this.mLayoutArrayReferences = layoutReferences;
    }

    public void setItemCount(int itemCount) {
        this.mItemCount = itemCount;
    }

    public void setShimmer(Shimmer shimmer) {
        this.mShimmer = shimmer;
    }

    public int getCorrectLayoutItem(int position) {
        if (doesArrayOfLayoutsExist()) {
            return mLayoutArrayReferences[position % mLayoutArrayReferences.length];
        }
        return mLayoutReference;
    }

    private boolean doesArrayOfLayoutsExist() {
        return mLayoutArrayReferences != null && mLayoutArrayReferences.length != 0;
    }
}
