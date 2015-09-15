package com.mlog.weather.anim.drawable;

import android.graphics.Rect;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.mlog.weather.anim.weatherItem.Cloud;
import com.mlog.weather.anim.weatherItem.FreezingRainDrop;
import com.mlog.weather.anim.weatherItem.IWeatherItem;
import com.mlog.weather.anim.weatherItem.IWeatherItemCallback;
import com.mlog.weather.anim.weatherItem.RainLine;

import java.util.List;
import java.util.Random;

/**
 * 冻雨
 *
 * @author CJL
 * @since 2015-09-15
 */
public class FreezingRainDrawable extends WeatherDrawable implements IWeatherItemCallback {

    // 雨滴状雨水数量
    static final int RAIN_DROP_COUNT = 4;
    // 连线雨水数量
    static final int RAIN_LINE_COUNT = 7;
    // 延迟下落最大时间
    static final int RAIN_DELAY = 300;

    Random random = new Random(System.currentTimeMillis());

    Rect mRainRect;
    int mXShift;
    int mLineMaxLen;
    int mLineMinLen;

    @Override
    void addWeatherItem(List<IWeatherItem> weatherItems, Rect rect) {
        Cloud cloud = new Cloud();
        cloud.setBounds(rect);
        weatherItems.add(cloud);

        int w = rect.width();
//        mXShift = (int) (57f / 250 * w);
        mXShift = 0;
        mLineMaxLen = (int) (40f / 250 * w);
        mLineMinLen = (int) (15f / 250 * w);

        int rw = (int) (rect.width() * 190f / 250);
        int left = (rect.width() - rw) / 2 + mXShift;
        int top = (int) (rect.width() * 120f / 250);
        mRainRect = new Rect(left, top, left + rw - mXShift, rect.bottom);

        for (int i = 0; i < RAIN_DROP_COUNT; i++) {
            weatherItems.add(getRandomRainDrop(null, mRainRect, true));
        }
        for (int i = 0; i < RAIN_LINE_COUNT; i++) {
            weatherItems.add(getRandomRainLine(null, mRainRect, true));
        }
    }


    /**
     * 随机生成雨滴状雨水
     *
     * @param rainDrop 雨滴状雨水
     * @param rect     雨滴显示区域
     * @return 雨滴状雨水对象
     */
    FreezingRainDrop getRandomRainDrop(@Nullable FreezingRainDrop rainDrop, Rect rect, boolean isFirst) {
        if (rainDrop == null) {
            rainDrop = new FreezingRainDrop();
        }

        int dropWidth = (int) (2.23f / 115 * rect.width());

        rainDrop.setDelay(random.nextInt(isFirst ? RAIN_DELAY : 800));
        int x = rect.left + random.nextInt(rect.width() - dropWidth);
        rainDrop.setBounds(new Rect(x, rect.top, x + dropWidth, rect.bottom));
//        rainDrop.setXShift(mXShift);

        rainDrop.setCallback(this);
        return rainDrop;
    }


    /**
     * 随机生成连线状雨水
     *
     * @param rainLine 雨滴状雨水
     * @param rect     雨滴显示区域
     * @return 雨滴状雨水对象
     */
    RainLine getRandomRainLine(@Nullable RainLine rainLine, Rect rect, boolean isFirst) {
        if (rainLine == null) {
            rainLine = new RainLine();
        }

        int lineWidth = (int) (1f / 115 * rect.width());

        rainLine.setDelay(random.nextInt(isFirst ? RAIN_DELAY : 800));
        int x = rect.left + random.nextInt(rect.width() - lineWidth);
        rainLine.setBounds(new Rect(x, rect.top, x + lineWidth, rect.bottom));
        rainLine.setXShift(mXShift);
        rainLine.setLen(mLineMinLen, mLineMaxLen);

        rainLine.setCallback(this);
        return rainLine;
    }

    @Override
    public void onAnimFinish(IWeatherItem item) {
        if (!mIsRunning) {
            return;
        }
        if (item instanceof RainLine) {
            RainLine rainLine = getRandomRainLine((RainLine) item, mRainRect, false);
            rainLine.start(SystemClock.elapsedRealtime());
        } else if (item instanceof FreezingRainDrop) {
            FreezingRainDrop rainDrop = getRandomRainDrop((FreezingRainDrop) item, mRainRect, false);
            rainDrop.start(SystemClock.elapsedRealtime());
        }
    }
}
