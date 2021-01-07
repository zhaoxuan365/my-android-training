package com.zhaoxuan.shimmerskeleton;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * author : zhaoxuan
 * date : 2021/1/4
 * desc : https://github.com/ethanhua/Skeleton
 */
public class ViewReplacer {

    private final View mSourceView;
    private final ViewGroup.LayoutParams mSourceViewLayoutParams;
    private View mCurrentView;
    private final int mSourceViewId;
    private int mTargetViewResID = -1;
    private ViewGroup mSourceParentView;
    private int mSourceViewIndexInParent;
    private View mTargetView;

    public ViewReplacer(View sourceView) {
        mSourceView = sourceView;
        mSourceViewLayoutParams = mSourceView.getLayoutParams();
        mCurrentView = mSourceView;
        mSourceViewId = mSourceView.getId();
    }

    public void replaceByResID(int targetViewResID) {
        if (mTargetViewResID == targetViewResID) {
            return;
        }
        if (init()) {
            mTargetViewResID = targetViewResID;
            replaceByView(LayoutInflater.from(mSourceView.getContext()).inflate(mTargetViewResID,
                    mSourceParentView, false));
        }
    }

    private boolean init() {
        if (mSourceParentView == null) {
            mSourceParentView = (ViewGroup) mSourceView.getParent();
            if (mSourceParentView == null) {
                // the source view have not attach to any view
                return false;
            }
            int count = mSourceParentView.getChildCount();
            for (int index = 0; index < count; index++) {
                if (mSourceView == mSourceParentView.getChildAt(index)) {
                    mSourceViewIndexInParent = index;
                    break;
                }
            }
        }
        return true;
    }


    public void replaceByView(View targetView) {
        if (mCurrentView == targetView) {
            return;
        }
        if (targetView.getParent() != null) {
            ((ViewGroup) targetView.getParent()).removeView(targetView);
        }
        if (init()) {
            mTargetView = targetView;
            mSourceParentView.removeView(mCurrentView);
            mTargetView.setId(mSourceViewId);
            mSourceParentView.addView(mTargetView, mSourceViewIndexInParent, mSourceViewLayoutParams);
            mCurrentView = mTargetView;
        }
    }


    public View getSourceView() {
        return mSourceView;
    }

    public View getTargetView() {
        return mTargetView;
    }

    public View getCurrentView() {
        return mCurrentView;
    }

    public void restore() {
        if (mSourceParentView != null) {
            mSourceParentView.removeView(mCurrentView);
            mSourceParentView.addView(mSourceView, mSourceViewIndexInParent, mSourceViewLayoutParams);
            mCurrentView = mSourceView;
            mTargetView = null;
            mTargetViewResID = -1;
        }
    }
}
