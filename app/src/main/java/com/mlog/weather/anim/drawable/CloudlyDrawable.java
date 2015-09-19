package com.mlog.weather.anim.drawable;

import android.graphics.Rect;

import com.mlog.weather.anim.weatherItem.Cloudly;
import com.mlog.weather.anim.weatherItem.IWeatherItem;

import java.util.List;

/**
 * 阴天
 *
 * @author CJL
 * @since 2015-09-19
 */
public class CloudlyDrawable extends WeatherDrawable {
    @Override
    void addWeatherItem(List<IWeatherItem> weatherItems, Rect rect) {
        Cloudly cloudly = new Cloudly();
        cloudly.setBounds(rect.left, rect.top, rect.right, rect.bottom);

        weatherItems.add(cloudly);
    }
}
