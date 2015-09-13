package com.mlog.weather.anim.weatherItem;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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

    // 下落总长度
    float dropLen;
    double angle;

    @Override
    public void setBounds(Rect rect) {
        super.setBounds(rect);

        int h = mBounds.height();
        dropLen = (float) Math.sqrt(mXShift * mXShift + h * h);
        angle = Math.atan2(h, mXShift);
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint, long time) {
        if (mStartTime == -1) {
            return;
        }

        int t = (int) (time - mStartTime);
        if (t <= mDelayTime) {
            return;
        }


        int w = mBounds.width();
        paint.setStrokeWidth(w);

        float progress = mInterpolator.getInterpolation(t * 1f / ANIM_DURATION);
        float p1x = mBounds.centerX() - mXShift * progress;
        float p1y = mBounds.top + mBounds.height() * progress;

        float progressDrop = mInterpolator.getInterpolation(t * 1f / DROP_DURATION);
        float len = t < DROP_DURATION ? mMaxLen * progressDrop : mMaxLen;
        int alpha = t < DROP_DURATION ? (int) (255 * progressDrop) : 255;

        float p2x = (float) (p1x + len * Math.sin(angle));
        float p2y = (float) (p1y - len * Math.cos(angle));

        paint.setColor(Color.argb(alpha, 255, 255, 255));
        if (t < DROP_DURATION) {
            canvas.drawLine(p1x, p1y, p2x, p2y, paint);
        } else if (t < ANIM_DURATION) {

        } else {
            stop();
        }
    }
}
