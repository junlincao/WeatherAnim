package com.mlog.weather.anim.drawable;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.example.cjl.weatheranim.R;
import com.mlog.weather.anim.weatherItem.IWeatherItem;
import com.mlog.weather.anim.weatherItem.Sun;

import java.util.List;

/**
 * 晴天
 *
 * @author CJL
 * @since 2015-09-15
 */
public class SunDrawable extends WeatherDrawable {

    Drawable mD1;
    Drawable mD2;
    Drawable mD3;

    public SunDrawable(Context context) {
        mD1 = context.getResources().getDrawable(R.drawable.sunny_1);
        mD2 = context.getResources().getDrawable(R.drawable.sunny_2);
        mD3 = context.getResources().getDrawable(R.drawable.sunny_3);
    }

    @Override
    void addWeatherItem(List<IWeatherItem> weatherItems, Rect rect) {
        Sun sun = new Sun(mD1, mD2, mD3);
        sun.setBounds(rect);
        sun.setShowWave(true);
        weatherItems.add(sun);
    }
}
