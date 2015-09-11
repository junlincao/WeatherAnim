package com.example.cjl.weatheranim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mlog.weather.anim.WeatherAnimView;
import com.mlog.weather.anim.drawable.HailDrawable;
import com.mlog.weather.anim.drawable.WeatherDrawable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherAnimView testView = (WeatherAnimView) findViewById(R.id.testView);

        WeatherDrawable drawable = new HailDrawable();
        testView.setBackgroundDrawable(drawable);
    }

}
