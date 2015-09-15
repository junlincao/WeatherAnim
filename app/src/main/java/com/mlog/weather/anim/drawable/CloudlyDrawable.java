package com.mlog.weather.anim.drawable;

import android.content.Context;
import android.graphics.Rect;

import com.mlog.weather.anim.weatherItem.Cloud;
import com.mlog.weather.anim.weatherItem.IWeatherItem;
import com.mlog.weather.anim.weatherItem.Sun;

import java.util.List;

/**
 * 多云
 *
 * @author CJL
 * @since 2015-09-15
 */
public class CloudlyDrawable extends SunDrawable {

    public CloudlyDrawable(Context context) {
        super(context);
    }

    @Override
    void addWeatherItem(List<IWeatherItem> weatherItems, Rect rect) {
        Sun sun = new Sun(mD1, mD2, mD3);
        sun.setShowWave(false);
        sun.setRoateSpeed(0, 360f / 30000, -360f / 60000);

        float w = rect.width();
        float hSunW = 120 / 420f * w; // 太阳一半宽度
        float sunCX = 180f / 250 * w;
        float sunCY = 110f / 250 * w;

        sun.setBounds(new Rect((int) (sunCX - hSunW), (int) (sunCY - hSunW), (int) (sunCX + hSunW), (int) (sunCY + hSunW)));
        weatherItems.add(sun);

        Cloud cloud = new Cloud();
        int cloudLeft = rect.left + (int) (-rect.width() * 0.08f);
        int cloudTop = rect.top + (int) (0.25f * rect.width());
        cloud.setBounds(new Rect(cloudLeft, cloudTop, cloudLeft + rect.width(), cloudTop + rect.height()));
        weatherItems.add(cloud);
    }
}