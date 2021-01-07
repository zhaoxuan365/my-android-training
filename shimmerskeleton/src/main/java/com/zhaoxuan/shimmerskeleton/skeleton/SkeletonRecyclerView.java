package com.zhaoxuan.shimmerskeleton.skeleton;

import androidx.annotation.ArrayRes;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

import com.zhaoxuan.shimmerskeleton.shimmer.Shimmer;


/**
 * author : zhaoxuan
 * date : 2021/1/4
 * desc : https://github.com/ethanhua/Skeleton
 */
public class SkeletonRecyclerView implements Skeleton {

    private final RecyclerView mRecyclerView;
    private final RecyclerView.Adapter<RecyclerView.ViewHolder> mActualAdapter;
    private final SkeletonAdapter mSkeletonAdapter;
    private final boolean mRecyclerViewFrozen;

    private SkeletonRecyclerView(Builder builder) {
        mRecyclerView = builder.mRecyclerView;
        mActualAdapter = builder.mActualAdapter;

        mSkeletonAdapter = new SkeletonAdapter();
        mSkeletonAdapter.setItemCount(builder.mItemCount);
        mSkeletonAdapter.setLayoutReference(builder.mItemResID);
        mSkeletonAdapter.setArrayOfLayoutReferences(builder.mItemsResIDArray);
        mSkeletonAdapter.setShimmer(builder.mShimmer);
        mRecyclerViewFrozen = builder.mFrozen;
    }

    @Override
    public void show() {
        mRecyclerView.setAdapter(mSkeletonAdapter);
        if (!mRecyclerView.isComputingLayout() && mRecyclerViewFrozen) {
            mRecyclerView.suppressLayout(true);
        }
    }

    @Override
    public void hide() {
        mRecyclerView.setAdapter(mActualAdapter);
    }

    public static class Builder {
        private RecyclerView.Adapter<RecyclerView.ViewHolder> mActualAdapter;
        private final RecyclerView mRecyclerView;
        private int mItemCount = 10;
        private int mItemResID;
        private int[] mItemsResIDArray;
        private boolean mFrozen = true;
        private Shimmer mShimmer;

        public Builder(RecyclerView recyclerView) {
            this.mRecyclerView = recyclerView;
        }

        /**
         * @param adapter the target recyclerView actual adapter
         */
        public Builder adapter(RecyclerView.Adapter adapter) {
            this.mActualAdapter = adapter;
            return this;
        }

        /**
         * @param itemCount the child item count in recyclerView
         */
        public Builder count(int itemCount) {
            this.mItemCount = itemCount;
            return this;
        }

        public Builder shimmer(Shimmer shimmer) {
            this.mShimmer = shimmer;
            return this;
        }

        /**
         * @param skeletonLayoutResID the loading skeleton layoutResID
         */
        public Builder load(@LayoutRes int skeletonLayoutResID) {
            this.mItemResID = skeletonLayoutResID;
            return this;
        }

        /**
         * @param skeletonLayoutResIDs the loading array of skeleton layoutResID
         */
        public Builder loadArrayOfLayouts(@ArrayRes int[] skeletonLayoutResIDs) {
            this.mItemsResIDArray = skeletonLayoutResIDs;
            return this;
        }

        /**
         * @param frozen whether frozen recyclerView during skeleton showing
         * @return
         */
        public Builder frozen(boolean frozen) {
            this.mFrozen = frozen;
            return this;
        }

        public SkeletonRecyclerView show() {
            SkeletonRecyclerView recyclerViewSkeleton = new SkeletonRecyclerView(this);
            recyclerViewSkeleton.show();
            return recyclerViewSkeleton;
        }
    }
}
