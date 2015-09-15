package com.mlog.weather.anim.weatherItem;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
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
    private Bitmap mSnow;
    int degree = 0;
    Random random = new Random(System.currentTimeMillis());
    public SnowDrop(Bitmap bitmap) {
        mSnow = bitmap;
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
                alpha = 255 * t / ALPHA_CENTER;
            } else {
                alpha = 255 - 255 * (t - ALPHA_CENTER) / (ANIM_DURATION - ALPHA_CENTER);
            }
            paint.setColor(Color.argb(alpha, 0, 0, 0));

            float progress = mInterpolator.getInterpolation((float) t / ANIM_DURATION);
            float x = mBounds.centerX() ; //- mXShift * progress
            float y = mBounds.top + mBounds.height() * progress;
            degree += random.nextInt(5);
            degree = 720 * 2 + degree;
            Log.d("snow", "degree:" + degree);
//            rotate(mSnow, degree)
            canvas.drawBitmap(rotate(mSnow, degree), x, y, paint);
//            canvas.drawCircle(x, y, mBounds.width() / 2, paint);
        } else {
            stop();
        }
    }

    /**
     * release data
     */
    public void release() {
        mSnow.recycle();
    }

    public static Bitmap rotate(Bitmap b, int degrees) {
        if (degrees != 0 && b != null) {
            Matrix m = new Matrix();
            m.setRotate(degrees,
                    (float) b.getWidth() / 2, (float) b.getHeight() / 2);
            try {
                Bitmap b2 = Bitmap.createBitmap(
                        b, 0, 0, b.getWidth(), b.getHeight(), m, true);
                if (b != b2) {
//                    b.recycle();  //Bitmap操作完应该显示的释放
                    b = b2;
                }
            } catch (OutOfMemoryError e) {
                Log.e("", e.toString());
            }
        }
        return b;
    }
}
