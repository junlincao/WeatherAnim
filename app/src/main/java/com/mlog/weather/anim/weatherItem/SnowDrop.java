package com.mlog.weather.anim.weatherItem;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * 雪花除了下落外也要自转
 * Created by dongqi on 2015/9/13.
 */
public class SnowDrop extends SimpleWeatherItem {
    private int mXShift = 0;
    //动画持续时间 ms
    static final int ANIM_DURATION = 800;
    // 透明度100中间点
    static final int ALPHA_CENTER = 200;
    private Drawable mSnow;
    int degree = 0;
    boolean flag = false;
    Random random = new Random(System.currentTimeMillis());

    public SnowDrop(Drawable drawable) {
        mSnow = drawable;
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

    @Override
    public void setCallback(IWeatherItemCallback callback) {
        super.setCallback(callback);
    }

    @Override
    public void setBounds(Rect rect) {
        super.setBounds(rect);
    }

    @Override
    public void setInterpolator(Interpolator intercepter) {
        super.setInterpolator(intercepter);
    }

    @Override
    public void start(long time) {
        super.start(time);
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public int getStatus() {
        return super.getStatus();
    }

    @Override
    public void setDelay(int delay) {
        super.setDelay(delay);
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
        t -= mDelayTime;

        if (t < ANIM_DURATION) {
            int alpha;
            if (t < ALPHA_CENTER) {
                alpha = (int) (255f * t / ALPHA_CENTER);
            } else {
                alpha = (int) (255f - 255f * (t - ALPHA_CENTER) / (ANIM_DURATION - ALPHA_CENTER));
            }
            mSnow.setAlpha(alpha);
            float progress = mInterpolator.getInterpolation((float) t / ANIM_DURATION);
            float x = mBounds.centerX();
            float y = mBounds.top + mBounds.height() * progress;

            int hw = mSnow.getIntrinsicWidth() / 2;
            int hh = mSnow.getIntrinsicHeight() / 2;
            mSnow.setBounds((int) x - hw, (int) y - hh, (int) x + hw, (int) y + hh);
            canvas.save();
            canvas.rotate(120 * progress, x , y);
            mSnow.draw(canvas);
            canvas.restore();
        } else {
            stop();
        }
    }
}
