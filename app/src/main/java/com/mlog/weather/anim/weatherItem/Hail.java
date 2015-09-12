package com.mlog.weather.anim.weatherItem;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;

import java.util.Random;

/**
 * 冰雹
 *
 * @author CJL
 * @since 2015-09-11
 */
public class Hail extends SimpleWeatherItem {
    // 下落耗时
    private static final int DROP_TIME = 800;
    // 落地后飞溅耗时
    private static final int SPLIT_TIME = 400;
     //下落步骤中完全透明
    private static final float FULL_ALPHA_PROGRESS = 0.7f;

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

        if (t < DROP_TIME) {
            drawDrop(canvas, paint, t * 1f / DROP_TIME);
        } else if (t < DROP_TIME + SPLIT_TIME) {
            drawSplit(canvas, paint, (t - DROP_TIME) * 1f / SPLIT_TIME);
        } else {
            stop();
        }
    }


    private void drawDrop(Canvas canvas, Paint paint, float progress) {
        int alpha = progress >= FULL_ALPHA_PROGRESS ? 255 : (int) (progress / FULL_ALPHA_PROGRESS * 255);
        paint.setColor(Color.argb(alpha, 255, 255, 255));
        float y = mBounds.top + mBounds.height() * mInterpolator.getInterpolation(progress);
        float x = mBounds.centerX();

        canvas.drawCircle(x, y, mBounds.width() / 10, paint);
    }

    private void drawSplit(Canvas canvas, Paint paint, float progress) {
        //TODO
    }

}
