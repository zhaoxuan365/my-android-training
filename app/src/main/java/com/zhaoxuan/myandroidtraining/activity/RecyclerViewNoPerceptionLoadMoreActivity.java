package com.zhaoxuan.myandroidtraining.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhaoxuan.myandroidtraining.R;
import com.zhaoxuan.myandroidtraining.adapter.NoPerceptionLoadMoreAdapter;
import com.zhaoxuan.myandroidtraining.adapter.NoPerceptionLoadMoreAdapterWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zhaoxuan
 * date : 2021/1/18
 * desc :
 */
public class RecyclerViewNoPerceptionLoadMoreActivity extends AppCompatActivity {

    private List<String> localDataSet;
    private RecyclerView recyclerView;
    // TODO: 2021/1/21 如果第一次请求的数据没有铺满屏幕, 那么此时需要用户手动下拉刷新, 能否起到效果待测试
    private static final int SIZE = 20;
    private int initListSize = SIZE;
    private boolean canLoadMore = false;
    private NoPerceptionLoadMoreAdapterWrapper adapterWrapper;
    // 是否正在加载更多
    private boolean isLoadingMore = false;
    // 预加载阈值(即还剩多少个条目没有完全显示时触发加载更多)
    private static final int preLoadCount = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_no_perception_load_more);

        init();
    }

    private void init() {
        localDataSet = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapterWrapper = new NoPerceptionLoadMoreAdapterWrapper(new NoPerceptionLoadMoreAdapter(localDataSet));
        recyclerView.setAdapter(adapterWrapper);
//        adapterWrapper.setOnPreLoadListener(new NoPerceptionLoadMoreAdapterWrapper.OnPreLoadListener() {
//            @Override
//            public void onPreLoad() {
//                // TODO: 2021/1/21
//                Log.d("zx", "向上滚动到还有" + adapterWrapper.getPreloadItemCount() + "个条目未显示,"
//                        + "即倒数第" + (adapterWrapper.getPreloadItemCount() + 1) + "个条目时开始请求下一页数据");
//                if (isLoadingMore){
//                    return;
//                }
//                isLoadingMore = true;
//                getData();
//            }
//        }, 3);

        getData();

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                if (manager instanceof LinearLayoutManager) {
//                    int itemCount = manager.getItemCount();
//                    int lastVisibleItemPosition = ((LinearLayoutManager) manager).findLastVisibleItemPosition();
//                    if (dy == 0) {
//                        Log.d("zx", "lastVisibleItemPosition-" + lastVisibleItemPosition
//                                + "---itemCount-" + itemCount + "---preLoadCount-" + preLoadCount);
//                    }
//                    if (itemCount - lastVisibleItemPosition < preLoadCount && dy == 0) {
//                        // 说明第一次获取到的数据不满足初次向上滑动触发加载更多
//                        // TODO: 2021/1/22 获取下一页数据
//                        getData();
//                        Log.d("zx", "-----------第一次获取到的数据不满足初次向上滑动触发加载更多, 直接获取下一页数据---------------");
//                    } else if (dy > 0 && lastVisibleItemPosition >= itemCount - preLoadCount) {
//                        // 滑动过快的情况下, 达到预加载阈值时, onScrolled不一定会被调用
//                        // 正常或慢慢滑动时, onScrolled又可能被多次调用
//                        // 因此使用 lastVisibleItemPosition >= itemCount - preLoadCount解决第一个问题
//                        // 使用 isLoadingMore 解决第二个问题
//                        if (isLoadingMore) {
//                            // 如果正处于加载中, 不再获取新数据
//                            return;
//                        }
//                        isLoadingMore = true;
//                        getData();
//                    }
//                }
//            }
//        });
    }

    private void getData() {
        // TODO: 2021/1/21
        // 模拟可以上拉加载5次
//        canLoadMore = localDataSet.size() < SIZE * 5;
//        if (!canLoadMore) {
//            return;
//        }
        // 延迟500毫秒模拟网络请求
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO: 2021/1/21
                for (int i = localDataSet.size(); i < initListSize; i++) {
                    localDataSet.add("这是第" + (i + 1) + "个条目");
                }
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        // 刷新操作
                        adapterWrapper.notifyDataSetChanged();
                    }
                });
                initListSize += SIZE;
                isLoadingMore = false;
//        adapter.setIsPreLoading(false);
                // TODO: 2021/1/22
                Log.d("zx", "此时的条目总数------------------" + adapterWrapper.getItemCount());
            }
        }, 500);
    }
}
