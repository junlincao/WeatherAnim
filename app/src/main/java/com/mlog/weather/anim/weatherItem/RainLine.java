package com.mlog.weather.anim.weatherItem;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * 连线状的雨水
 *
 * @author CJL
 * @since 2015-09-12
 */
public class RainLine extends SimpleWeatherItem {
    // 漂移距离
    private int mXShift = 0;
    // 雨水连线最大长度
    private int mMaxLen;

    //动画持续时间
    static final int ANIM_DURATION = 1600;
    // 下降持续时间
    static final int DROP_DURATION = 800;

    public RainLine() {
        mInterpolator = new AccelerateDecelerateInterpolator();
    }

    /**
     * 设置X轴位置偏移量
     *
     * @param xShift 偏移量,左侧为正，右侧为负
     */
    public void setXShift(int xShift) {
        this.mXShift = xShift;
    }

    /**
     * 设置雨线最大长度
     *
     * @param maxLen 长度
     */
    public void setMaxLen(int maxLen) {
        mMaxLen = maxLen;
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint, long time) {
        //TODO
    }
}
