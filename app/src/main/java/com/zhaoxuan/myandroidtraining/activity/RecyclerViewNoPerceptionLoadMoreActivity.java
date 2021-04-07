package com.zhaoxuan.myandroidtraining.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zhaoxuan.myandroidtraining.R;
import com.zhaoxuan.myandroidtraining.adapter.BaseRecyclerAdapter;
import com.zhaoxuan.myandroidtraining.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zhaoxuan
 * date : 2021/1/18
 * desc :
 * 参考1 https://juejin.cn/post/6885146484791050247
 * 参考2 https://www.jianshu.com/p/b502c5b59998
 * <p>
 * 参考3 https://github.com/scwang90/SmartRefreshLayout/issues/974
 * 不满一屏的情况下，SmartRefreshLayout会拦截了RecyclerView的滚动事件，这个怎么处理？
 * <p>
 * 一页请求的数据尽量超过一屏吧，要处理就是adapter.setData 完 调用 RecyclerView.poseDelayed
 * 里去判断当前可视的最后一个item position == adapter.getItemCount() ，
 * true 就是不满一页然后手动调用onLoadMore(refreshlayout)
 * <p>
 * 参考4 https://www.cnblogs.com/baiqiantao/p/6958811.html
 * 参考5 https://blog.csdn.net/qibin0506/article/details/49716795
 */

public class RecyclerViewNoPerceptionLoadMoreActivity extends AppCompatActivity {

    private List<String> localDataSet;
    private RecyclerView recyclerView;
    // TODO: 2021/1/21 如果第一次请求的数据没有铺满屏幕, 那么此时需要用户手动下拉刷新, 能否起到效果待测试
    private static final int SIZE = 20;
    private int initListSize = SIZE;
    private boolean canLoadMore = false;
    // 是否正在加载更多
    private boolean isLoadingMore = false;
    // 预加载阈值(即还剩多少个条目没有完全显示时触发加载更多)
    private static final int preLoadCount = 5;
    private MyAdapter adapter;
    private boolean isRefreshing = false;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_no_perception_load_more);

        init();
    }

    private void init() {

        refreshLayout = findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isRefreshing = true;
                getData();
            }
        });

        localDataSet = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        addHeader();
        getData();

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();

        adapter.setOnPreLoadListener(new BaseRecyclerAdapter.OnPreLoadListener() {
            @Override
            public void onPreLoad() {
                if (isLoadingMore) {
                    return;
                }
                isLoadingMore = true;
                getData();
            }
        }, preLoadCount);

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

    private void addHeader() {
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                View header = LayoutInflater.from(RecyclerViewNoPerceptionLoadMoreActivity.this)
                        .inflate(R.layout.item_no_perception_header, recyclerView, false);
                TextView tvHeader = header.findViewById(R.id.tv);
                tvHeader.setText("我是header");
                adapter.setHeader(header);
                // TODO: 2021/1/26
                // 检查此时的列表是否可以向下滚动, 如果可以, 就使用代码实现列表滑动到顶部.
                // 添加header时如果也需要调接口, 并且这个接口比获取列表数据的接口速度慢, 就需要手动实列表滑动到顶部.
            }
        }, 500);
    }

    private void getData() {
        // TODO: 2021/1/21
        // 模拟可以上拉加载5次
//        canLoadMore = localDataSet.size() < SIZE * 5;
        if (!canLoadMore) {
            isLoadingMore = false;
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    View footer = LayoutInflater.from(RecyclerViewNoPerceptionLoadMoreActivity.this)
                            .inflate(R.layout.item_no_perception_footer, recyclerView, false);
                    TextView tvFooter = footer.findViewById(R.id.tv);
                    tvFooter.setText("到底了哦~");
                    adapter.setFooter(footer);
                }
            });
            return;
        }
        if (isRefreshing) {
            if (!localDataSet.isEmpty()) {
                localDataSet.clear();
            }
        }
        // 延迟500毫秒模拟网络请求
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO: 2021/1/21
                for (int i = localDataSet.size(); i < initListSize; i++) {
                    localDataSet.add("这是第" + (i + 1) + "个条目");
                }
                initListSize += SIZE;
                // 刷新操作
                if (!adapter.getDataList().isEmpty()) {
                    adapter.getDataList().clear();
                }
                adapter.addData(localDataSet);
                isLoadingMore = false;
                // TODO: 2021/1/22
                Log.d("zx", "此时的条目总数------------------" + adapter.getItemCount());
            }
        }, 500);
        isRefreshing = false;
        refreshLayout.setRefreshing(false);
    }
}
