package com.zhaoxuan.myandroidtraining.viewholder;

import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * author : zhaoxuan
 * date : 2021/1/7
 * desc :
 */
public class NewsViewHolder extends RecyclerView.ViewHolder{

    private final SparseArray<View> views = new SparseArray<>();

    public NewsViewHolder(View itemView) {
        super(itemView);
    }

    public <V extends View> V getView(int resId) {
        View v = views.get(resId);
        if (null == v) {
            v = itemView.findViewById(resId);
            views.put(resId, v);
        }
        return (V) v;
    }
}
