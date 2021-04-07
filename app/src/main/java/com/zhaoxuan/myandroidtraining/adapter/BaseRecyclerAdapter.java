package com.zhaoxuan.myandroidtraining.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zhaoxuan
 * date : 2021/1/26
 * desc : 可添加header footer 以及预加载更多的adapter
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // header类型
    public static final int TYPE_HEADER = 0;
    // 普通条目类型
    public static final int TYPE_ITEM = 1;
    // footer类型
    public static final int TYPE_FOOTER = 2;
    private View header;
    private View footer;
    private final List<T> dataList = new ArrayList<>();
    // 触发预加载的阈值
    private int preloadItemCount = 0;
    // 列表是否处于向上滚动
    private boolean isScrollUp = false;
    private int scrollState = RecyclerView.SCROLL_STATE_IDLE;

    public void setHeader(View header) {
        this.header = header;
        notifyItemInserted(0);
    }

    public void setFooter(View footer) {
        this.footer = footer;
        notifyItemInserted(getItemCount() - 1);
    }

    public View getHeader() {
        return header;
    }

    public View getFooter() {
        return footer;
    }

    public void addData(List<T> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public List<T> getDataList() {
        return dataList;
    }

    @Override
    public int getItemViewType(int position) {
        if (header != null && position == 0) {
            return TYPE_HEADER;
        }
        if (footer != null && position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        if (header == null && footer == null) {
            return dataList.size();
        } else if (header != null && footer != null) {
            return dataList.size() + 2;
        } else {
            return dataList.size() + 1;
        }
    }

    public int getPreloadItemCount() {
        return preloadItemCount;
    }

    private OnPreLoadListener onPreLoadListener;

    public interface OnPreLoadListener {
        void onPreLoad();
    }

    public void setOnPreLoadListener(OnPreLoadListener onPreLoadListener, int preloadItemCount) {
        this.onPreLoadListener = onPreLoadListener;
        this.preloadItemCount = preloadItemCount;
    }

    private void checkPreload(int position) {
        if (onPreLoadListener != null
                && isScrollUp
                && position == Math.max(getItemCount() - preloadItemCount, 0)
//                && scrollState != RecyclerView.SCROLL_STATE_IDLE // 预加载时机和滚动状态是静止还是滚动中貌似关系不大
        ) {
            onPreLoadListener.onPreLoad();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_HEADER && header != null) {
            return new ViewHolder(header);
        } else if (viewType == TYPE_FOOTER && footer != null) {
            return new ViewHolder(footer);
        } else {
            return onViewHolderCreate(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ITEM) {
            position = header == null ? position : position - 1;
            T t = dataList.get(position);
            onViewHolderBind(holder, position, t);
        }
        checkPreload(position);
    }

    protected abstract RecyclerView.ViewHolder onViewHolderCreate(ViewGroup parent, int viewType);

    protected abstract void onViewHolderBind(RecyclerView.ViewHolder holder, int position, T t);

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            // TODO: 2021/1/26
        } else if (layoutManager instanceof GridLayoutManager) {
            // TODO: 2021/1/26
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            // TODO: 2021/1/26
        }

        recyclerView.addOnScrollListener(new androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull androidx.recyclerview.widget.RecyclerView recyclerView, int newState) {
                // 更新列表的滚动状态
                scrollState = newState;
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull androidx.recyclerview.widget.RecyclerView recyclerView, int dx, int dy) {
                isScrollUp = dy > 0;
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
