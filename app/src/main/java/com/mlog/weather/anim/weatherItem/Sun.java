package com.mlog.weather.anim.weatherItem;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * 太阳
 *
 * @author CJL
 * @since 2015-09-15
 */
public class Sun extends SimpleWeatherItem {
    // 扩散波形持续时间
    static final int WAVE_DURATION = 4000;
    // 扩散波形
    static final int WAVE_DELTA = 5000;
    // 第2个扩散波形延迟
    static final int WAVE_DELAY = 500;

    // 第一张图片旋转速度 度每毫秒
    static final float SPEED_P1 = 360f / 15000;
    // 第一张图片旋转速度 度每毫秒
    static final float SPEED_P2 = -360f / 60000;
    // 第一张图片旋转速度 度每毫秒
    static final float SPEED_P3 = 360f / 30000;

    private Drawable mD1;
    private Drawable mD2;
    private Drawable mD3;

    private float mSp1 = SPEED_P1;
    private float mSp2 = SPEED_P2;
    private float mSp3 = SPEED_P3;


    private boolean mShowWave = true;

    public Sun(Drawable d1, Drawable d2, Drawable d3) {
        this.mD1 = d1;
        this.mD2 = d2;
        this.mD3 = d3;

        mInterpolator = new AccelerateDecelerateInterpolator();
    }

    /**
     * 设置旋转速度 负值代表逆时针转动, 为0则不显示
     *
     * @param sp1 第一张图片旋转速度
     * @param sp2 第二张图片旋转速度
     * @param sp3 第三张图片旋转速度
     */
    public void setRoateSpeed(float sp1, float sp2, float sp3) {
        this.mSp1 = sp1;
        this.mSp2 = sp2;
        this.mSp3 = sp3;
    }

    /**
     * 设置是否显示波纹
     */
    public void setShowWave(boolean showWave) {
        mShowWave = showWave;
    }

    @Override
    public void setBounds(Rect rect) {
        super.setBounds(rect);

        float scale = (202f / 250) * rect.width() / mD3.getIntrinsicWidth();

        int hD3W = (int) (mD3.getIntrinsicWidth() * scale * 0.5f);
        mD3.setBounds(rect.centerX() - hD3W, rect.centerY() - hD3W, rect.centerX() + hD3W, rect.centerY() + hD3W);

        int hD2W = (int) (mD2.getIntrinsicWidth() * scale * 0.5f);
        mD2.setBounds(rect.centerX() - hD2W, rect.centerY() - hD2W, rect.centerX() + hD2W, rect.centerY() + hD2W);

        int hD1W = (int) (mD1.getIntrinsicWidth() * scale * 0.5f);
        mD1.setBounds(rect.centerX() - hD1W, rect.centerY() - hD1W, rect.centerX() + hD1W, rect.centerY() + hD1W);
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint, long time) {
        if (mStartTime == -1) {
            return;
        }

        int t = (int) (time - mStartTime);

        float centerX = mBounds.centerX();
        float centerY = mBounds.centerY();

        if (mSp1 != 0) {
            canvas.save();
            canvas.rotate(t * mSp1, centerX, centerY);
            mD1.draw(canvas);
            canvas.restore();
        }

        if (mSp2 != 0) {
            canvas.save();
            canvas.rotate(t * mSp2, centerX, centerY);
            mD2.draw(canvas);
            canvas.restore();
        }

        if (mSp3 != 0) {
            canvas.save();
            canvas.rotate(t * mSp3, centerX, centerY);
            mD3.draw(canvas);
            canvas.restore();
        }

        paint.setColor(Color.WHITE);
        canvas.drawCircle(centerX, centerY, 50f / 250 * mBounds.width(), paint);

        paint.setColor(0x99ffffff);
        canvas.drawCircle(centerX, centerY, 62.5f / 250 * mBounds.width(), paint);

        if (mShowWave) {
            t %= WAVE_DELTA;
            for (int i = 0; i < 2; i++) {
                t -= WAVE_DELAY * i;
                if (t > 0 && t < WAVE_DURATION) {
                    float progress = mInterpolator.getInterpolation(t * 1f / WAVE_DURATION);
                    int alpha = (int) (255 * (1 - progress));
                    paint.setColor(Color.argb(alpha, 255, 255, 255));

                    float r = 112.5f / 250 * mBounds.width() * progress;
                    canvas.drawCircle(centerX, centerY, r, paint);
                }
            }
        }
    }
}
