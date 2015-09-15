package com.example.cjl.weatheranim;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.mlog.weather.anim.drawable.CloudDrawable;
import com.mlog.weather.anim.drawable.HailDrawable;
import com.mlog.weather.anim.drawable.RainDrawable;
import com.mlog.weather.anim.drawable.SnowDrawable;
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

        datas.put("冰雹", new HailDrawable());
        datas.put("多云", new CloudDrawable());
        datas.put("大雨", new RainDrawable(RainDrawable.RAIN_TYPE_HEAVY));
        datas.put("小雨", new RainDrawable(RainDrawable.RAIN_TYPE_SMALL));

        Bitmap[] bitmaps = { ((BitmapDrawable)getResources().getDrawable(R.drawable.snow)).getBitmap(),
                ((BitmapDrawable)getResources().getDrawable(R.drawable.snow_2x)).getBitmap() ,
                ((BitmapDrawable)getResources().getDrawable(R.drawable.snow_2x)).getBitmap()};
        datas.put("雪", new SnowDrawable(bitmaps));

        return datas;
    }

}
