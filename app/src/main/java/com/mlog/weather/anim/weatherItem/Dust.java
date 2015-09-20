package com.mlog.weather.anim.weatherItem;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * 尘埃
 *
 * @author CJL
 * @since 2015-09-20
 */
public class Dust extends SimpleWeatherItem {

    int halfSize;
    float speed;
    int alphaStartLen;

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);


        // 若宽度大于高度，则表示从左往右移动，否则从下往上移动
        halfSize = Math.min(mBounds.width(), mBounds.height());
        speed = halfSize * 15f / 1000;

        alphaStartLen = (int) (Math.max(mBounds.width(), mBounds.height()) * 0.15f);
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint, long time) {
        if (mStartTime == -1) {
            return;
        }
        int t = (int) (time - mStartTime);

        int centerX, centerY;
        int alpha = 255;
        if (mBounds.width() > mBounds.height()) {
            centerY = mBounds.centerY();
            centerX = mBounds.left + (int) (-halfSize + speed * t);
            if (centerX > mBounds.right) {
                stop();
                return;
            }
            if (centerX - mBounds.left > alphaStartLen) {
                alpha = (int) (255 - 255f * (centerX - mBounds.left - alphaStartLen) / (mBounds.width() - alphaStartLen));
            }
        } else {
            centerX = mBounds.centerX();
            centerY = (int) (mBounds.bottom + halfSize - speed * t);
            if (centerY < mBounds.top) {
                stop();
                return;
            }
            if (mBounds.bottom - centerY > alphaStartLen) {
                alpha = (int) (255 - 255f * (mBounds.bottom - centerY - alphaStartLen) / (mBounds.height() - alphaStartLen));
            }
        }
        paint.setColor(Color.argb(alpha, 255, 255, 255));

        canvas.save();
        canvas.rotate(45, centerX, centerY);
        canvas.drawRect(centerX - halfSize, centerY - halfSize, centerX + halfSize, centerY + halfSize, paint);
        canvas.restore();
    }
}
