package com.zhaoxuan.myandroidtraining.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhaoxuan.myandroidtraining.R;

import java.util.List;

/**
 * author : zhaoxuan
 * date : 2021/1/18
 * desc :
 * 参考1 https://juejin.cn/post/6885146484791050247
 * 参考2 https://www.jianshu.com/p/b502c5b59998
 *
 * 参考3 https://github.com/scwang90/SmartRefreshLayout/issues/974
 * 不满一屏的情况下，SmartRefreshLayout会拦截了RecyclerView的滚动事件，这个怎么处理？
 *
 * 一页请求的数据尽量超过一屏吧，要处理就是adapter.setData 完 调用 RecyclerView.poseDelayed
 * 里去判断当前可视的最后一个item position == adapter.getItemCount() ，
 * true 就是不满一页然后手动调用onLoadMore(refreshlayout)
 *
 * 参考4 https://www.cnblogs.com/baiqiantao/p/6958811.html
 */
public class NoPerceptionLoadMoreAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final RecyclerView.Adapter adapter;
    private int scrollState = RecyclerView.SCROLL_STATE_IDLE;
    private int preloadItemCount = 0;
    /**
     * 是否正处于预加载中
     */
//    private boolean isPreLoading = false;
    private boolean isScrollUp = false;

//    public void setIsPreLoading(boolean isPreLoading){
//        this.isPreLoading = isPreLoading;
//    }

    public NoPerceptionLoadMoreAdapterWrapper(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public int getPreloadItemCount() {
        return preloadItemCount;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return adapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        adapter.onBindViewHolder(holder, position);
        checkPreload(position);
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount();
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
                && position == Math.max(getItemCount() - preloadItemCount, 0)
//                && scrollState != RecyclerView.SCROLL_STATE_IDLE // todo 预加载时机和滚动状态是静止还是滚动中貌似关系不大?
//                && !isPreLoading // todo 让业务层维护这个标记位比较好? 因为若Adapter只单纯地提供预加载时机，它就不需要关心业务层加载何时结束。
                && isScrollUp) {
//            isPreLoading = true;
            // TODO: 2021/1/21
            Log.d("zx", "---------在当前条目position请求下一页数据------------" + position);
            onPreLoadListener.onPreLoad();
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
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
}
