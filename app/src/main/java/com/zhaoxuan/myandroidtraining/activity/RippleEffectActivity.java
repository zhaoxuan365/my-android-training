package com.zhaoxuan.myandroidtraining.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhaoxuan.myandroidtraining.R;

/**
 * author : zhaoxuan
 * date : 2021/1/13
 * desc :
 */
public class RippleEffectActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ripple_effect);

        findViewById(R.id.bordered_ripple).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bordered_ripple) {
            Intent intent = new Intent(this, RippleEffectBorderedActivity.class);
            startActivity(intent);
        }
    }
}
