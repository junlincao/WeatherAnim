package com.mlog.weather.anim.drawable;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.example.cjl.weatheranim.R;
import com.mlog.weather.anim.weatherItem.IWeatherItem;
import com.mlog.weather.anim.weatherItem.Moon;

import java.util.List;

/**
 * 晴夜晚
 *
 * @author CJL
 * @since 2015-09-18
 */
public class MoonDrawable extends WeatherDrawable {

    Drawable mMoonDrawable;

    public MoonDrawable(Context context) {
        mMoonDrawable = context.getResources().getDrawable(R.drawable.moon);
    }

    @Override
    void addWeatherItem(List<IWeatherItem> weatherItems, Rect rect) {
        Moon moon = new Moon(mMoonDrawable);
        moon.setBounds(rect.left, rect.top, rect.right, rect.bottom);
        weatherItems.add(moon);
    }

    @Override
    protected void addRandomItem(List<IWeatherRandomItem> randomItems, Rect rect) {
        super.addRandomItem(randomItems, rect);
    }
}
