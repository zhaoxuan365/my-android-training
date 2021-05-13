package com.zhaoxuan.myandroidtraining.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhaoxuan.myandroidtraining.R;
import com.zhaoxuan.myandroidtraining.widget.GridItemDividerTransparentVertical;

import java.util.ArrayList;
import java.util.List;

/**
 * author : zhaoxuan
 * date : 2021/5/10
 * desc :
 */
public class RecyclerViewItemDecorationGridVerticalActivity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RecyclerViewItemDecorationGridVerticalActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_item_decoration_grid_vertical);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        GridItemDividerTransparentVertical itemDivider =
                new GridItemDividerTransparentVertical(this,16, true);
        recyclerView.addItemDecoration(itemDivider);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("我是条目" + i);
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
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_text, parent, false);
            return new MyAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
            holder.tv.setText(sourceData.get(position));
        }

        @Override
        public int getItemCount() {
            return sourceData.size();
        }
    }
}
