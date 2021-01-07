package com.zhaoxuan.shimmerskeleton.skeleton;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.LayoutRes;

import com.zhaoxuan.shimmerskeleton.R;
import com.zhaoxuan.shimmerskeleton.ViewReplacer;
import com.zhaoxuan.shimmerskeleton.shimmer.Shimmer;
import com.zhaoxuan.shimmerskeleton.shimmer.ShimmerFrameLayout;


/**
 * author : zhaoxuan
 * date : 2021/1/4
 * desc : https://github.com/ethanhua/Skeleton
 */
public class SkeletonView implements Skeleton {

    private final ViewReplacer mViewReplacer;
    private final View mActualView;
    private final int mSkeletonResID;
    private final Shimmer mShimmer;

    private SkeletonView(Builder builder) {
        mActualView = builder.mView;
        mSkeletonResID = builder.mSkeletonLayoutResID;
        mShimmer = builder.mShimmer;
        mViewReplacer = new ViewReplacer(builder.mView);
    }

    public static class Builder {
        private final View mView;
        private Shimmer mShimmer;
        private int mSkeletonLayoutResID;

        public Builder(View view) {
            this.mView = view;
        }

        /**
         * @param skeletonLayoutResID the loading skeleton layoutResID
         */
        public Builder load(@LayoutRes int skeletonLayoutResID) {
            this.mSkeletonLayoutResID = skeletonLayoutResID;
            return this;
        }

        public Builder shimmer(Shimmer shimmer) {
            this.mShimmer = shimmer;
            return this;
        }

        public SkeletonView show() {
            SkeletonView skeletonScreen = new SkeletonView(this);
            skeletonScreen.show();
            return skeletonScreen;
        }
    }

    @Override
    public void show() {
        View skeletonLoadingView = generateSkeletonLoadingView();
        if (skeletonLoadingView != null) {
            mViewReplacer.replaceByView(skeletonLoadingView);
        }
    }

    @Override
    public void hide() {
        if (mViewReplacer.getTargetView() instanceof ShimmerFrameLayout) {
            ((ShimmerFrameLayout) mViewReplacer.getTargetView()).stopShimmer();
        }
        mViewReplacer.restore();
    }

    private View generateSkeletonLoadingView() {
        ViewParent viewParent = mActualView.getParent();
        if (viewParent == null) {
            // the source view have not attach to any view
            return null;
        }
        ViewGroup parentView = (ViewGroup) viewParent;
        return generateShimmerContainerLayout(parentView);
    }

    private ShimmerFrameLayout generateShimmerContainerLayout(ViewGroup parentView) {
        final ShimmerFrameLayout shimmerLayout = (ShimmerFrameLayout) LayoutInflater.from(mActualView.getContext())
                .inflate(R.layout.layout_shimmer, parentView, false);
        if (mShimmer != null) {
            shimmerLayout.setShimmer(mShimmer);
        }
        View innerView = LayoutInflater.from(mActualView.getContext()).inflate(mSkeletonResID, shimmerLayout, false);
        ViewGroup.LayoutParams lp = innerView.getLayoutParams();
        if (lp != null) {
            shimmerLayout.setLayoutParams(lp);
        }
        shimmerLayout.addView(innerView);
        shimmerLayout.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                shimmerLayout.startShimmer();
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                shimmerLayout.startShimmer();
            }
        });
        shimmerLayout.startShimmer();
        return shimmerLayout;
    }
}
