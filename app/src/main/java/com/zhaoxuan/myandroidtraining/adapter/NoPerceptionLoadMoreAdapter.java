package com.zhaoxuan.myandroidtraining.adapter;

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
public class NoPerceptionLoadMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<String> localDataSet;

    public NoPerceptionLoadMoreAdapter(List<String> localDataSet) {
        this.localDataSet = localDataSet;
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public static class HeaderHolder extends RecyclerView.ViewHolder {

        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class FooterHolder extends RecyclerView.ViewHolder {

        public FooterHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case NoPerceptionLoadMoreAdapterWrapper.TYPE_HEADER: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_no_perception_header, parent, false);
                return new ItemHolder(view);
            }
            case NoPerceptionLoadMoreAdapterWrapper.TYPE_FOOTER: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_no_perception_footer, parent, false);
                return new ItemHolder(view);
            }
            case NoPerceptionLoadMoreAdapterWrapper.TYPE_ITEM: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_no_perception, parent, false);
                return new ItemHolder(view);
            }
        }
        return onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderHolder) {
            // TODO: 2021/1/22
        } else if (holder instanceof FooterHolder) {
            // TODO: 2021/1/22
        } else if (holder instanceof ItemHolder) {
            ((ItemHolder) holder).getTextView().setText(localDataSet.get(position));
        } else {
            onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
