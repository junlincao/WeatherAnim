package com.mlog.weather.anim.weatherItem;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.IntDef;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 天气动画元素
 *
 * @author CJL
 * @since 2015-09-11
 */
public interface IWeatherItem {
    @IntDef({STATUS_NOT_START, STATUS_RUNNING})
    @Retention(RetentionPolicy.SOURCE)
    @interface WeatherItemStatus {
    }

    int STATUS_NOT_START = 0;
    int STATUS_RUNNING = 1;

    void setCallback(IWeatherItemCallback callback);

    void setBounds(int left, int top, int right, int bottom);

//    void reset();

    void start(long time);

    void stop();

    void onDraw(Canvas canvas, Paint paint, long time);

    @WeatherItemStatus
    int getStatus();

    void setInterpolator(Interpolator intercepter);
}
