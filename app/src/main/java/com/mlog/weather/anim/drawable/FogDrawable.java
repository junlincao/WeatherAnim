package com.mlog.weather.anim.drawable;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.example.cjl.weatheranim.R;
import com.mlog.weather.anim.weatherItem.Fog;
import com.mlog.weather.anim.weatherItem.IWeatherItem;

import java.util.List;

/**
 * 雾
 *
 * @author CJL
 * @since 2015-09-20
 */
public class FogDrawable extends WeatherDrawable {

    Drawable mBg;
    Drawable mFog;

    public FogDrawable(Context context) {
        mBg = context.getResources().getDrawable(R.drawable.fog_bg);
        mFog = context.getResources().getDrawable(R.drawable.fog);
    }

    @Override
    void addWeatherItem(List<IWeatherItem> weatherItems, Rect rect) {
        Fog fog = new Fog(mBg, mFog);
        fog.setBounds(rect.left, rect.top, rect.right, rect.bottom);
        weatherItems.add(fog);
    }
}
