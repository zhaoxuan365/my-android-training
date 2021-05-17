package com.zhaoxuan.myandroidtraining.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

/**
 * author : zhaoxuan
 * date : 2021/5/10
 * desc : https://github.com/youlookwhat/ByRecyclerView
 * <p>
 * 给LinearLayoutManager(垂直&水平)添加分割线，
 * 可设置列表顶部和底部不添加分割线的条目个数，
 * 可通过drawable 或 直接指定分割线高度+颜色来添加分割线，
 * 可指定分割线距离条目两边的距离(即分割线的长度或高度可以<=条目的长度或高度)
 */
public class LinearItemDivider extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;
    private final Context mContext;
    private Drawable mDivider;
    private final Rect mBounds = new Rect();
    /**
     * 在AppTheme里配置 android:listDivider
     */
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    /**
     * 头部 不显示分割线的item个数 这里应该包含刷新头，
     * 比如有一个headerView和有下拉刷新，则这里传 2
     */
    private int mHeaderNoShowSize;
    /**
     * 尾部 不显示分割线的item个数 默认显示最后一个item的分割线，如不显示就传1
     */
    private int mFooterNoShowSize;
    /**
     * Current orientation. Either {@link #HORIZONTAL} or {@link #VERTICAL}.
     */
    private int mOrientation;
    private Paint mPaint;
    /**
     * 如果是横向 - 宽度
     * 如果是纵向 - 高度
     */
    private int mDividerSpacing;
    /**
     * 如果是横向 - 左边距
     * 如果是纵向 - 上边距
     */
    private int mLeftOrTopPadding;
    /**
     * 如果是横向 - 左边距
     * 如果是纵向 - 上边距
     */
    private int mLeftOrTopPaddingOfFirstItem;
    /**
     * 如果是横向 - 右边距
     * 如果是纵向 - 下边距
     */
    private int mRightOrBottomPadding;
    private RecyclerView recyclerView;

    /**
     * Creates a divider {@link RecyclerView.ItemDecoration}
     *
     * @param context Current context, it will be used to access resources.
     */
    public LinearItemDivider(Context context) {
        this(context, VERTICAL, 0, 0);
    }

    /**
     * Creates a divider {@link RecyclerView.ItemDecoration}
     *
     * @param context     Current context, it will be used to access resources.
     * @param orientation Divider orientation. Should be {@link #HORIZONTAL} or {@link #VERTICAL}.
     */
    public LinearItemDivider(Context context, int orientation) {
        this(context, orientation, 0, 0);
    }

    /**
     * Creates a divider {@link RecyclerView.ItemDecoration}
     *
     * @param context          Current context, it will be used to access resources.
     * @param orientation      Divider orientation. Should be {@link #HORIZONTAL} or {@link #VERTICAL}.
     * @param headerNoShowSize headerViewSize + RefreshViewSize
     */
    public LinearItemDivider(Context context, int orientation, int headerNoShowSize) {
        this(context, orientation, headerNoShowSize, 0);
    }

    /**
     * Creates a divider {@link RecyclerView.ItemDecoration}
     *
     * @param context          Current context, it will be used to access resources.
     * @param orientation      Divider orientation. Should be {@link #HORIZONTAL} or {@link #VERTICAL}.
     * @param headerNoShowSize headerViewSize + RefreshViewSize
     * @param footerNoShowSize footerViewSize
     */
    public LinearItemDivider(Context context, int orientation, int headerNoShowSize, int footerNoShowSize) {
        mContext = context;
        mHeaderNoShowSize = headerNoShowSize;
        mFooterNoShowSize = footerNoShowSize;
        setOrientation(orientation);
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    /**
     * Sets the orientation for this divider. This should be called if
     * {@link RecyclerView.LayoutManager} changes orientation.
     *
     * @param orientation {@link #HORIZONTAL} or {@link #VERTICAL}
     */
    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        mOrientation = orientation;
    }

    /**
     * Sets the {@link Drawable} for this divider.
     *
     * @param drawable Drawable that should be used as a divider.
     */
    public LinearItemDivider setDrawable(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("drawable cannot be null.");
        }
        mDivider = drawable;
        return this;
    }

    public LinearItemDivider setDrawable(@DrawableRes int id) {
        setDrawable(ContextCompat.getDrawable(mContext, id));
        return this;
    }

    @Override
    public void onDraw(@NotNull Canvas canvas, RecyclerView parent, @NotNull RecyclerView.State state) {
        if (parent.getLayoutManager() == null || (mDivider == null && mPaint == null)) {
            return;
        }
        if (mOrientation == VERTICAL) {
            drawVertical(canvas, parent, state);
        } else {
            drawHorizontal(canvas, parent, state);
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        canvas.save();
        final int left;
        final int right;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left,
                    parent.getPaddingTop(),
                    right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        final int childCount = parent.getChildCount();
        final int lastPosition = state.getItemCount() - 1;
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final int childRealPosition = parent.getChildAdapterPosition(child);

            // 过滤到头部不显示的分割线
            if (childRealPosition < mHeaderNoShowSize) {
                continue;
            }
            // 过滤到尾部不显示的分割线
            if (childRealPosition <= lastPosition - mFooterNoShowSize) {
                if (mDivider != null) {
                    parent.getDecoratedBoundsWithMargins(child, mBounds);
                    final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
                    final int top = bottom - mDivider.getIntrinsicHeight();
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(canvas);
                }

                if (mPaint != null) {
                    RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                    int left1 = left + mLeftOrTopPadding;
                    int right1 = right - mRightOrBottomPadding;
                    int top1 = child.getBottom() + params.bottomMargin;
                    int bottom1 = top1 + mDividerSpacing;
                    canvas.drawRect(left1, top1, right1, bottom1, mPaint);
                }
            }
        }
        canvas.restore();
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        canvas.save();
        final int top;
        final int bottom;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(),
                    top,
                    parent.getWidth() - parent.getPaddingRight(),
                    bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        final int childCount = parent.getChildCount();
        final int lastPosition = state.getItemCount() - 1;
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final int childRealPosition = parent.getChildAdapterPosition(child);

            // 过滤到头部不显示的分割线
            if (childRealPosition < mHeaderNoShowSize) {
                continue;
            }
            // 过滤到尾部不显示的分割线
            if (childRealPosition <= lastPosition - mFooterNoShowSize) {
                if (mDivider != null) {
                    parent.getDecoratedBoundsWithMargins(child, mBounds);
                    final int right = mBounds.right + Math.round(child.getTranslationX());
                    final int left = right - mDivider.getIntrinsicWidth();
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(canvas);
                }

                if (mPaint != null) {
                    RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                    int left1 = child.getRight() + params.rightMargin;
                    int right1 = left1 + mDividerSpacing;
                    int top1 = top + mLeftOrTopPadding;
                    int bottom1 = bottom - mRightOrBottomPadding;
                    canvas.drawRect(left1, top1, right1, bottom1, mPaint);
                }
            }
        }
        canvas.restore();
    }

    @Override
    public void getItemOffsets(@NotNull Rect outRect, @NotNull View view,
                               @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {
        if (mDivider == null && mPaint == null) {
            outRect.set(0, 0, 0, 0);
            return;
        }
        //parent.getChildCount() 不能拿到item的总数
        int lastPosition = state.getItemCount() - 1;
        int position = parent.getChildAdapterPosition(view);

        if (recyclerView == null) {
            recyclerView = parent;
        }

        boolean isShowDivider = mHeaderNoShowSize <= position
                && position <= lastPosition - mFooterNoShowSize;

        if (mOrientation == VERTICAL) {
            if (isShowDivider) {
                // 如果是第一个条目，可以设置marginTop值
                if (0 == parent.getChildAdapterPosition(view)) {
                    outRect.set(mLeftOrTopPadding,
                            mLeftOrTopPaddingOfFirstItem,
                            mRightOrBottomPadding,
                            mDivider != null ? mDivider.getIntrinsicHeight() : mDividerSpacing);
                } else {
                    outRect.set(mLeftOrTopPadding,
                            0,
                            mRightOrBottomPadding,
                            mDivider != null ? mDivider.getIntrinsicHeight() : mDividerSpacing);
                }
            } else {
                outRect.set(0, 0, 0, 0);
            }
        } else {
            if (isShowDivider) {
                // 如果是第一个条目， 可以设置marginLeft值
                if (0 == parent.getChildAdapterPosition(view)) {
                    outRect.set(mLeftOrTopPaddingOfFirstItem,
                            mLeftOrTopPadding,
                            mDivider != null ? mDivider.getIntrinsicWidth() : mDividerSpacing,
                            mRightOrBottomPadding);
                } else {
                    outRect.set(0,
                            mLeftOrTopPadding,
                            mDivider != null ? mDivider.getIntrinsicWidth() : mDividerSpacing,
                            mRightOrBottomPadding);
                }
            } else {
                outRect.set(0, 0, 0, 0);
            }
        }
    }

    /**
     * 设置不显示分割线的item位置与个数
     *
     * @param headerNoShowSize 头部 不显示分割线的item个数
     * @param footerNoShowSize 尾部 不显示分割线的item个数，如果设合为1，即不显示最后一个,最后一个一般为加载更多view
     */
    public LinearItemDivider setNoShowDivider(int headerNoShowSize, int footerNoShowSize) {
        this.mHeaderNoShowSize = headerNoShowSize;
        this.mFooterNoShowSize = footerNoShowSize;
        return this;
    }

    /**
     * 设置不显示头部分割线的item个数
     *
     * @param headerNoShowSize 头部 不显示分割线的item个数
     */
    public LinearItemDivider setHeaderNoShowDivider(int headerNoShowSize) {
        this.mHeaderNoShowSize = headerNoShowSize;
        return this;
    }

    /**
     * 直接设置分割线颜色等，不设置drawable
     *
     * @param dividerColor     分割线颜色
     * @param dividerSpacingDp 分割线间距 如间距16(即代表16dp，无需转换)
     */
    public LinearItemDivider setParam(int dividerColor, float dividerSpacingDp) {
        return setParam(dividerColor,
                dividerSpacingDp,
                0,
                0,
                0);
    }

    /**
     * 直接设置分割线颜色等，不设置drawable
     *
     * @param dividerColor                  分割线颜色 0xXXXXXX或Color.xxx
     * @param dividerSpacingDp              分割线间距 如间距16(即代表16dp，无需转换)
     * @param leftOrTopPaddingDp            如果是横向 - 左边距 如间距16(即代表16dp，无需转换)
     *                                      如果是纵向 - 上边距 如间距16(即代表16dp，无需转换)
     * @param rightOrBottomPaddingDp        如果是横向 - 右边距 如间距16(即代表16dp，无需转换)
     *                                      如果是纵向 - 下边距 如间距16(即代表16dp，无需转换)
     * @param leftOrTopPaddingDpOfFirstItem 如果是横向 - 左边距 如间距16(即代表16dp，无需转换)
     *                                      如果是纵向 - 上边距 如间距16(即代表16dp，无需转换)
     */
    public LinearItemDivider setParam(int dividerColor,
                                      float dividerSpacingDp,
                                      float leftOrTopPaddingDp,
                                      float rightOrBottomPaddingDp,
                                      float leftOrTopPaddingDpOfFirstItem) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(dividerColor);
        mDividerSpacing = dp2px(dividerSpacingDp);
        mLeftOrTopPadding = dp2px(leftOrTopPaddingDp);
        mRightOrBottomPadding = dp2px(rightOrBottomPaddingDp);
        mLeftOrTopPaddingOfFirstItem = dp2px(leftOrTopPaddingDpOfFirstItem);
        mDivider = null;
        return this;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dp2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
