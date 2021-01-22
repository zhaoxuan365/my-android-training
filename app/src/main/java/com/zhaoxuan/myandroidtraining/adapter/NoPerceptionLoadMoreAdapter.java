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
 * desc : https://juejin.cn/post/6885146484791050247
 */
public class NoPerceptionLoadMoreAdapter extends RecyclerView.Adapter<NoPerceptionLoadMoreAdapter.ViewHolder> {

    private final List<String> localDataSet;

    public NoPerceptionLoadMoreAdapter(List<String> localDataSet) {
        this.localDataSet = localDataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    @NonNull
    @Override
    public NoPerceptionLoadMoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_no_perception, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoPerceptionLoadMoreAdapter.ViewHolder holder, int position) {
        holder.getTextView().setText(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
