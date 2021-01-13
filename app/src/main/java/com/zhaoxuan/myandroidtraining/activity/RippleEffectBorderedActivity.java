package com.zhaoxuan.myandroidtraining.activity;

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
public class RippleEffectBorderedActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ripple_effect_bordered);

        // 实现点击/按压的水波纹效果, 要么注册点击事件, 要么在布局文件中添加android:clickable="true"
//        findViewById(R.id.bordered_ripple).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
