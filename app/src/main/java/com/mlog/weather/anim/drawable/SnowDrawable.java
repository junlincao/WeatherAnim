package com.mlog.weather.anim.drawable;

import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mlog.weather.anim.weatherItem.Cloud;
import com.mlog.weather.anim.weatherItem.IWeatherItem;
import com.mlog.weather.anim.weatherItem.IWeatherItemCallback;
import com.mlog.weather.anim.weatherItem.SnowDrop;

/**
 * Created by dongqi on 2015/9/13.
 *
 * @since 2015.09.13
 */
public class SnowDrawable extends WeatherDrawable implements IWeatherItemCallback {
    Rect mRainRect;
    int SNOW_COUNT = 10;
    int mXShift;
    int mLineMaxLen;
    // 延迟下落最大时间
    static final int RAIN_DELAY = 300;
    Random random = new Random(System.currentTimeMillis());
    Drawable[] mDrawables;

    public SnowDrawable(Drawable[] drawables) {
        mDrawables = drawables;
    }

    @Override
    void addWeatherItem(List<IWeatherItem> weatherItems, Rect rect) {
        Cloud cloud = new Cloud();
        cloud.setBounds(rect);
        weatherItems.add(cloud);
        //添加雪花
        int hw = (int) (rect.width() * 190f / 250);
        int left = (rect.width() - hw) / 2;
        int top = (int) (rect.width() * 120f / 250);
        mRainRect = new Rect(left, top, left + hw, rect.bottom);
        for (int i = 0; i < SNOW_COUNT; i++) {
            weatherItems.add(getRandomSnowDrop(null, mRainRect, true));
        }
    }

    /**
     * 随机生成雨滴状雨水
     *
     * @param rainDrop 雨滴状雨水
     * @param rect     雨滴显示区域
     * @return 雨滴状雨水对象
     */
    SnowDrop getRandomSnowDrop(@Nullable SnowDrop rainDrop, Rect rect, boolean isFirst) {
        if (rainDrop == null) {
            int subIndex = random.nextInt(3);
            rainDrop = new SnowDrop(mDrawables[subIndex]);
        }
        int hailWidth = (int) (27f / 190 * rect.width());
        rainDrop.setDelay(random.nextInt(isFirst ? 800 : 300));
        int x = rect.left + random.nextInt(rect.width() - hailWidth);
        rainDrop.setBounds(new Rect(x, rect.top, x + hailWidth, rect.bottom));
        rainDrop.setCallback(this);
        return rainDrop;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public void startAnimation() {
        super.startAnimation();
    }

    @Override
    public void stopAnimation() {
        super.stopAnimation();
    }

    @Override
    public void setAlpha(int alpha) {
        super.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return super.getOpacity();
    }

    @Override
    protected List<IWeatherItem> getWeatherItems() {
        return super.getWeatherItems();
    }

    @Override
    public void onAnimFinish(IWeatherItem item) {
        Log.i("snow", "onAnimFinish");
        if (!mIsRunning) {
            return;
        }
        item = getRandomSnowDrop((SnowDrop) item, mRainRect, false);
        item.start(SystemClock.elapsedRealtime());
    }
}
