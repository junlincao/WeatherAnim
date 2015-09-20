package com.example.cjl.weatheranim;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.mlog.weather.anim.drawable.CloudlyDrawable;
import com.mlog.weather.anim.drawable.CloudsDrawable;
import com.mlog.weather.anim.drawable.FogDrawable;
import com.mlog.weather.anim.drawable.FreezingRainDrawable;
import com.mlog.weather.anim.drawable.HailDrawable;
import com.mlog.weather.anim.drawable.HazeDrawable;
import com.mlog.weather.anim.drawable.MoonDrawable;
import com.mlog.weather.anim.drawable.RainAndSnowDrawable;
import com.mlog.weather.anim.drawable.RainDrawable;
import com.mlog.weather.anim.drawable.SandStormDrawable;
import com.mlog.weather.anim.drawable.ShowerDrawable;
import com.mlog.weather.anim.drawable.SnowDrawable;
import com.mlog.weather.anim.drawable.SnowShowerDrawable;
import com.mlog.weather.anim.drawable.SunDrawable;
import com.mlog.weather.anim.drawable.ThundersShowerDrawable;
import com.mlog.weather.anim.drawable.WeatherDrawable;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);

        AnimPagerAdapter mAdapter = new AnimPagerAdapter(getLayoutInflater());
        mAdapter.updateData(generateData());

        mViewPager.setAdapter(mAdapter);

    }

    private Map<String, WeatherDrawable> generateData() {
        LinkedHashMap<String, WeatherDrawable> datas = new LinkedHashMap<>();

        datas.put("晴白天", new SunDrawable(this));
        datas.put("晴夜晚", new MoonDrawable(this));
        datas.put("白天多云", new CloudsDrawable(this, CloudsDrawable.TYPE_DAY));
        datas.put("晚上多云", new CloudsDrawable(this, CloudsDrawable.TYPE_NIGHT));
        datas.put("阴天", new CloudlyDrawable());
        datas.put("白天阵雨", new ShowerDrawable(this, CloudsDrawable.TYPE_DAY));
        datas.put("晚上阵雨", new ShowerDrawable(this, CloudsDrawable.TYPE_NIGHT));
        datas.put("雷阵雨", new ThundersShowerDrawable(this));
        datas.put("冰雹", new HailDrawable());
        datas.put("雨夹雪", new RainAndSnowDrawable(this));
        datas.put("小雨", new RainDrawable(RainDrawable.RAIN_TYPE_SMALL));
        datas.put("大雨", new RainDrawable(RainDrawable.RAIN_TYPE_HEAVY));
        datas.put("白天阵雪", new SnowShowerDrawable(this, CloudsDrawable.TYPE_DAY));
        datas.put("晚上阵雪", new SnowShowerDrawable(this, CloudsDrawable.TYPE_NIGHT));
        datas.put("雪", new SnowDrawable(this));
        datas.put("雾", new FogDrawable(this));
        datas.put("冻雨", new FreezingRainDrawable());
        datas.put("霾", new HazeDrawable(this));
        datas.put("沙尘暴", new SandStormDrawable(this));

        return datas;
    }

}
