package com.mlog.weather.anim.drawable;

import android.graphics.Rect;

import com.mlog.weather.anim.weatherItem.Cloud;
import com.mlog.weather.anim.weatherItem.IWeatherItem;

import java.util.List;

/**
 * 多云
 *
 * @author CJL
 * @since 2015-09-11
 */
public class CloudDrawable extends WeatherDrawable {
    @Override
    void addWeatherItem(List<IWeatherItem> weatherItems, Rect rect) {
        Cloud cloud = new Cloud();
        cloud.setBounds(rect);

        weatherItems.add(cloud);
    }
}
