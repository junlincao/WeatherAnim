package com.mlog.weather.anim.weatherItem;

import android.graphics.Rect;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * 处理IWeatherItem通用方法
 *
 * @author CJL
 * @since 2015-09-11
 */
public abstract class SimpleWeatherItem implements IWeatherItem {

    protected int mDelayTime = 0;

    protected IWeatherItemCallback mCallBack;

    protected Rect mBounds = new Rect();

    protected Interpolator mInterpolator = new AccelerateInterpolator();

    /**
     * 动画开始时间
     **/
    protected long mStartTime;

    protected int mStatus = STATUS_NOT_START;

//    protected int mRepeatMode = MODE_REPEAT;


    @Override
    public void setCallback(IWeatherItemCallback callback) {
        mCallBack = callback;
    }

    @Override
    public void setBounds(Rect rect) {
        mBounds.set(rect);
    }

    @Override
    public void start(long time) {
        mStartTime = time;
        mStatus = STATUS_RUNNING;
    }

    @Override
    public void stop() {
        mStartTime = -1;

        mStatus = STATUS_ENDED;
        if (mCallBack != null) {
            mCallBack.onAnimFinish(this);
        }
    }

    @Override
    public int getStatus() {
        return mStatus;
    }

    @Override
    public void setInterpolator(Interpolator intercepter) {
        if (intercepter != null) {
            mInterpolator = intercepter;
        }
    }

    @Override
    public void setDelay(int delay) {
        mDelayTime = delay;
    }

//    @Override
//    public void setRepeatMode(@WeatherItemRepeatMode int repeatMode) {
//        mRepeatMode = repeatMode;
//    }
}
