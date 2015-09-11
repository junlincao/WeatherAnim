package com.mlog.weather.anim.drawable;

import android.graphics.Rect;

import com.mlog.weather.anim.weatherItem.Hail;
import com.mlog.weather.anim.weatherItem.IWeatherItem;

import java.util.List;

/**
 * 冰雹
 *
 * @author CJL
 * @since 2015-09-11
*/
public class HailDrawable extends WeatherDrawable {


    @Override
    void addWeatherItem(List<IWeatherItem> weatherItems) {
        int x = 0;
        Hail hail = new Hail();
        hail.setBounds(new Rect(x, 30, x + 80, 300));
        weatherItems.add(hail);

        x = 10;
        hail = new Hail();
        hail.setDelay(500);
        hail.setBounds(new Rect(x, 30, x + 80, 300));
        weatherItems.add(hail);

        x = 30;
        hail = new Hail();
        hail.setDelay(150);
        hail.setBounds(new Rect(x, 30, x + 80, 300));
        weatherItems.add(hail);

        x = 110;
        hail = new Hail();
        hail.setDelay(300);
        hail.setBounds(new Rect(x, 30, x + 80, 300));
        weatherItems.add(hail);


        x = 70;
        hail = new Hail();
        hail.setDelay(700);
        hail.setBounds(new Rect(x, 30, x + 80, 300));
        weatherItems.add(hail);
    }
}
