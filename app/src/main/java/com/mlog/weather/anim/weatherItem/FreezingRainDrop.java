package com.mlog.weather.anim.weatherItem;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.animation.AccelerateInterpolator;

/**
 * 冻雨
 *
 * @author CJL
 * @since 2015-09-15
 */
public class FreezingRainDrop extends SimpleWeatherItem {
    // 下落耗时
    private static final int DROP_TIME = 800;

    //下落步骤中完全透明
    private static final float FULL_ALPHA_PROGRESS = 0.7f;


    public FreezingRainDrop() {
        mInterpolator = new AccelerateInterpolator();
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
        if (t > DROP_TIME) {
            stop();
            return;
        }

        float progress = t * 1f / DROP_TIME;

        int alpha = progress >= FULL_ALPHA_PROGRESS ? 255 : (int) (progress / FULL_ALPHA_PROGRESS * 255);
        paint.setColor(Color.argb(alpha, 255, 255, 255));
        float y = mBounds.top + mBounds.height() * mInterpolator.getInterpolation(progress);
        float x = mBounds.centerX();

        canvas.save();
        canvas.rotate(45, x, y);

        float hRectW = mBounds.width() / 2;
        canvas.drawRect(x - hRectW, y - hRectW, x + hRectW, y + hRectW, paint);

        canvas.restore();

    }
}
