package com.zhaoxuan.myandroidtraining.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhaoxuan.myandroidtraining.R;
import com.zhaoxuan.shimmerskeleton.ViewReplacer;


/**
 * author : zhaoxuan
 * date : 2021/1/7
 * desc :
 */
public class ViewReplacerUsingActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewReplacer viewReplacer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_replacer_using);

        viewReplacer = new ViewReplacer(findViewById(R.id.layout_content));

        findViewById(R.id.btn_loading).setOnClickListener(this);
        findViewById(R.id.btn_empty).setOnClickListener(this);
        findViewById(R.id.btn_load_error).setOnClickListener(this);
        findViewById(R.id.btn_load_content).setOnClickListener(this);
    }

    public void gotoNetworkSet(View view) {
        Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_loading) {
            viewReplacer.replaceByResID(R.layout.layout_loading);
        } else if (v.getId() == R.id.btn_empty) {
            viewReplacer.replaceByResID(R.layout.layout_empty);
        } else if (v.getId() == R.id.btn_load_error) {
            viewReplacer.replaceByResID(R.layout.layout_error);
        }else if (v.getId() == R.id.btn_load_content) {
            viewReplacer.restore();
        }
    }
}
