package com.mlog.weather.anim.weatherItem;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

/**
 * 月亮
 *
 * @author CJL
 * @since 2015-09-18
 */
public class Moon extends SimpleWeatherItem {
    private Drawable mDrawable;

    public Moon(Drawable moonDrawable) {
        mDrawable = moonDrawable;
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);

        float scale = 162f * mBounds.width() / (250 * mDrawable.getIntrinsicWidth());
        int hw = (int) (mDrawable.getIntrinsicWidth() * scale * 0.5f);
        int hh = (int) (mDrawable.getIntrinsicHeight() * scale * 0.5f);

        int centerX = mBounds.centerX();
        int centerY = mBounds.centerY();
        mDrawable.setBounds(centerX - hw, centerY - hh, centerX + hw, centerY + hh);
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint, long time) {
        if (mStartTime == -1) {
            return;
        }
        mDrawable.draw(canvas);
    }
}
