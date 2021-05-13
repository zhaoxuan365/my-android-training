package com.zhaoxuan.myandroidtraining.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.zhaoxuan.myandroidtraining.R;
import com.zhaoxuan.myandroidtraining.util.DensityUtils;
import com.zhaoxuan.myandroidtraining.widget.GridItemDividerTransparentVertical;
import com.zhaoxuan.myandroidtraining.widget.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zhaoxuan
 * date : 2021/5/10
 * desc :
 */
public class RecyclerViewItemDecorationStaggeredGridVerticalActivity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RecyclerViewItemDecorationStaggeredGridVerticalActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_item_decoration_grid_vertical);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager gridLayoutManager
                = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        GridItemDividerTransparentVertical itemDivider =
                new GridItemDividerTransparentVertical(this, 16, true);
        recyclerView.addItemDecoration(itemDivider);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if (i % 2 == 1) { // 奇数
                list.add("我是条目" + i);
            } else { // 偶数
                list.add("我是条目我是条目我是条目我是条目" + i);
            }
        }
        MyAdapter adapter = new MyAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private final List<String> sourceData;

        public MyAdapter(List<String> sourceData) {
            this.sourceData = sourceData;
        }

        private static class ViewHolder extends RecyclerView.ViewHolder {

            private final TextView tv;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                tv = itemView.findViewById(R.id.tv);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_text4, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.tv.setText(sourceData.get(position));
        }

        @Override
        public int getItemCount() {
            return sourceData.size();
        }
    }
}
