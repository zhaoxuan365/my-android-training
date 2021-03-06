package com.zhaoxuan.myandroidtraining.util;

import android.content.Context;

/**
 * author : zhaoxuan
 * date : 2021/5/10
 * desc :
 */
public class DensityUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
