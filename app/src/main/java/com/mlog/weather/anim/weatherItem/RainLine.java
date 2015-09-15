package com.mlog.weather.anim.weatherItem;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

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
    // 雨水连线最小长度
    private int mMinLen;

    //动画持续时间
    static final int ANIM_DURATION = 1600;
    // 下降持续时间
    static final int DROP_DURATION = 900;
    // 扩展开始时间
    static final int EXPAND_START = 800;
    // 扩展渐隐开始时间
    static final int EXPAND_ALPHA_START = 1200;

    // 下落总长度
    float dropLen;
    double angle;

    public RainLine() {
//        mInterpolator = new AccelerateDecelerateInterpolator();
        mInterpolator = new AccelerateInterpolator(0.8f);
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
     * @param minLen 最小长度
     * @param maxLen 最大长度
     */
    public void setLen(int minLen, int maxLen) {
        mMaxLen = maxLen;
        mMinLen = minLen;
    }

    @Override
    public void setBounds(Rect rect) {
        super.setBounds(rect);

        int h = mBounds.height();
        dropLen = (float) Math.sqrt(mXShift * mXShift + h * h);
        angle = Math.atan2(h, mXShift);
    }

    Interpolator mExpandInterpolator = new AccelerateInterpolator();

    @Override
    public void onDraw(Canvas canvas, Paint paint, long time) {
        if (mStartTime == -1) {
            return;
        }

        int t = (int) (time - mStartTime);
        if (t <= mDelayTime) {
            return;
        }
        t -= mDelayTime;

        int w = mBounds.width();
        paint.setStrokeWidth(w);

        if (t > ANIM_DURATION) {
            stop();
            return;
        }

        if (t < DROP_DURATION) {
            float progressDrop = mInterpolator.getInterpolation(t * 1f / DROP_DURATION);
            float len = t < DROP_DURATION ? mMinLen + (mMaxLen - mMinLen) * progressDrop : mMaxLen;
            int alpha = t < DROP_DURATION ? (int) (255 * progressDrop) : 255;

            float p2x = mBounds.centerX() - mXShift * progressDrop;
            float p2y = mBounds.top - mMinLen + mBounds.height() * progressDrop;
            float p1x = (float) (p2x - len * Math.cos(angle));
            float p1y = (float) (p2y + len * Math.sin(angle));

            paint.setColor(Color.argb(alpha, 255, 255, 255));
            canvas.drawLine(p1x, p1y, p2x, p2y, paint); //TODO 应该旋转画布画圆角矩形
        }
        if (t > EXPAND_START) {
            float pbx = mBounds.centerX() - mXShift;
//            float pby = mBounds.bottom - mBounds.width() / 2f - 1;
            int alpha = t < EXPAND_ALPHA_START ? 255 : (int) (255 - 255f * (t - EXPAND_ALPHA_START) / (ANIM_DURATION - EXPAND_ALPHA_START));
            float len = mMaxLen * mExpandInterpolator.getInterpolation((t - EXPAND_START) * 1f / (ANIM_DURATION - EXPAND_START));
            paint.setColor(Color.argb(alpha, 255, 255, 255));
//            canvas.drawLine(pbx - len / 2, pby, pbx + len / 2, pby, paint);

            tmpRect.set(pbx - len / 2, mBounds.bottom - mBounds.width(), pbx + len / 2, mBounds.bottom);
            canvas.drawRoundRect(tmpRect, mBounds.width() / 2, mBounds.width() / 2, paint);
        }
    }

    RectF tmpRect = new RectF();
}
