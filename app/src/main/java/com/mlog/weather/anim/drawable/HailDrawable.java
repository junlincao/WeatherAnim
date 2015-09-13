package com.mlog.weather.anim.drawable;

import android.graphics.Rect;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.mlog.weather.anim.weatherItem.Cloud;
import com.mlog.weather.anim.weatherItem.Hail;
import com.mlog.weather.anim.weatherItem.IWeatherItem;
import com.mlog.weather.anim.weatherItem.IWeatherItemCallback;

import java.util.List;
import java.util.Random;

/**
 * 冰雹天气
 *
 * @author CJL
 * @since 2015-09-11
 */
public class HailDrawable extends WeatherDrawable implements IWeatherItemCallback {
    // 冰雹数量
    static final int HAIL_COUNT = 10;
    // 冰雹延迟下落最大时间
    static final int HAIL_DELAY = 300;

    Random random = new Random(System.currentTimeMillis());

    Rect mHailRect;

    @Override
    void addWeatherItem(List<IWeatherItem> weatherItems, Rect rect) {
        Cloud cloud = new Cloud();
        cloud.setBounds(rect);
        weatherItems.add(cloud);

        int hw = (int) (rect.width() * 190f / 250);
        int left = (rect.width() - hw) / 2;
        int top = (int) (rect.width() * 120f / 250);
        mHailRect = new Rect(left, top, left + hw, rect.bottom);

        for (int i = 0; i < HAIL_COUNT; i++) {
            weatherItems.add(getRandomHail(null, mHailRect, true));
        }
    }

    /**
     * 随机生成冰雹
     *
     * @param hail 冰雹对象
     * @param rect 冰雹显示区域
     * @return 冰雹对象
     */
    Hail getRandomHail(@Nullable Hail hail, Rect rect, boolean isFirst) {
        if (hail == null) {
            hail = new Hail();
        }

        int hailWidth = (int) (28f / 190 * rect.width());

        hail.setDelay(random.nextInt(isFirst ? HAIL_DELAY : 1200));
        int x = rect.left + random.nextInt(rect.width() - hailWidth);
        hail.setBounds(new Rect(x, rect.top, x + hailWidth, rect.bottom));

        hail.setCallback(this);
        return hail;
    }

    @Override
    public void onAnimFinish(IWeatherItem item) {
        if (!mIsRunning) {
            return;
        }
        if (item instanceof Hail) {
            Hail hail = getRandomHail((Hail) item, mHailRect, false);
            hail.start(SystemClock.elapsedRealtime());
        }
    }
}
