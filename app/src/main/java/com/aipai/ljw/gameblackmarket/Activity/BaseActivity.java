package com.aipai.ljw.gameblackmarket.Activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.aipai.ljw.gameblackmarket.R;

/**
 * Created by PC 303 on 2016/4/3.
 */
public class BaseActivity extends AppCompatActivity {



    protected void initToolbar(Toolbar toolbar){
        if (toolbar == null)
            return;
        toolbar.setBackgroundColor(Color.BLUE);
        toolbar.setTitle(R.string.app_name);
//        toolbar.setTitleTextColor(getColor(R.color.action_bar_title_color));
        toolbar.collapseActionView();
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);//去掉 title
//        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getSupportActionBar() != null){
            //设置箭头
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


}
