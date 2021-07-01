package com.zhaoxuan.myandroidtraining.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhaoxuan.myandroidtraining.R;

/**
 * author : zhaoxuan
 * date : 2021/1/7
 * desc :
 */
public class ConstraintLayoutUsingActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_constraint_layout_using);

        findViewById(R.id.multi_views_centered).setOnClickListener(this);
        findViewById(R.id.radio).setOnClickListener(this);
        findViewById(R.id.max_min).setOnClickListener(this);
        findViewById(R.id.constraint_set).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.multi_views_centered) {
            Intent intent = new Intent(this, ConstraintLayoutMultiViewsCenteredUsingActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.radio) {
            Intent intent = new Intent(this, ConstraintLayoutRadioUsingActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.max_min) {
            Intent intent = new Intent(this, ConstraintLayoutMaxMinUsingActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.constraint_set) {
            Intent intent = new Intent(this, ConstraintSetUsingActivity.class);
            startActivity(intent);
        }
    }
}
