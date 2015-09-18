package com.mlog.weather.anim.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import com.mlog.weather.anim.weatherItem.IWeatherItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 天气动画基类
 *
 * @author CJL
 * @since 2015-09-11
 */
public abstract class WeatherDrawable extends Drawable {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private ArrayList<IWeatherItem> mWeatherItems = new ArrayList<>();
    private LinkedList<IWeatherItem> mWeatherRandomItems = new LinkedList<>();

    private ArrayList<IWeatherRandomItem> mRandomItems = new ArrayList<>();

    protected boolean mIsRunning = false;

    /**
     * 启动动画
     * <p>
     * 除非调用stopAnimation，否则不会停止
     */
    public void startAnimation() {
        if (mIsRunning || mWeatherItems.size() == 0) {
            return;
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                invalidateSelf();
                mHandler.post(this);
            }
        });

        final long time = SystemClock.elapsedRealtime();
        for (IWeatherItem wi : mWeatherItems) {
            wi.start(time);
        }

        for (final IWeatherRandomItem wri : mRandomItems) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (!mIsRunning) {
                        return;
                    }
                    IWeatherItem iwi = wri.getRandomWeatherItem();
                    mWeatherRandomItems.add(iwi);
                    iwi.start(SystemClock.elapsedRealtime());
                    mHandler.postDelayed(this, wri.getInterval());
                }
            });
        }
        mIsRunning = true;
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);

        mWeatherItems.clear();
        mRandomItems.clear();
        addWeatherItem(mWeatherItems, getBounds());
        addRandomItem(mRandomItems, getBounds());
        if (!mIsRunning) {
            startAnimation();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        long time = SystemClock.elapsedRealtime();
        for (IWeatherItem wi : mWeatherItems) {
            wi.onDraw(canvas, mPaint, time);
        }

        Iterator ri = mWeatherRandomItems.iterator();
        while (ri.hasNext()) {
            IWeatherItem wi = (IWeatherItem) ri.next();
            if (wi.getStatus() == IWeatherItem.STATUS_ENDED) {
                ri.remove();
            } else {
                wi.onDraw(canvas, mPaint, time);
            }
        }
    }

    public void stopAnimation() {
        mHandler.removeCallbacksAndMessages(null);
        for (IWeatherItem wi : mWeatherItems) {
            wi.stop();
        }
        mIsRunning = false;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    protected Handler getHandler() {
        return mHandler;
    }

    protected List<IWeatherItem> getWeatherItems() {
        return mWeatherItems;
    }

    /**
     * 添加天气动态组件
     *
     * @param weatherItems 组件集合
     * @param rect         Drawable Bounds
     */
    abstract void addWeatherItem(List<IWeatherItem> weatherItems, Rect rect);

    /**
     * 添加自动随机生成天气元素
     *
     * @param randomItems 自动生成类
     * @param rect        Drawable Bounds
     */
    protected void addRandomItem(List<IWeatherRandomItem> randomItems, Rect rect) {
    }

}
