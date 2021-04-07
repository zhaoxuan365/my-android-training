package com.zhaoxuan.myandroidtraining.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhaoxuan.myandroidtraining.R;

/**
 * author : zhaoxuan
 * date : 2021/1/26
 * desc :
 */
public class MyAdapter extends BaseRecyclerAdapter<String> {

    @Override
    protected RecyclerView.ViewHolder onViewHolderCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_no_perception, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onViewHolderBind(RecyclerView.ViewHolder holder, int position, String t) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textView.setText(t);
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv);
        }

        public TextView getTextView() {
            return textView;
        }
    }

}
