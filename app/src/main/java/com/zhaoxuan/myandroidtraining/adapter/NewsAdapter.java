package com.zhaoxuan.myandroidtraining.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.zhaoxuan.myandroidtraining.R;
import com.zhaoxuan.myandroidtraining.viewholder.NewsViewHolder;

import org.jetbrains.annotations.NotNull;

/**
 * author : zhaoxuan
 * date : 2021/1/7
 * desc :
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    @NotNull
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NotNull NewsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

}
