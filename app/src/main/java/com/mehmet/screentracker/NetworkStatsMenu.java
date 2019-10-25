package com.mehmet.screentracker;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NetworkStatsMenu extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_stats_menu);

        viewPager=findViewById(R.id.networkViewPager);
        tabLayout=findViewById(R.id.networkTabLayout);

        NetwrokViewPagerAdapter adapter=new NetwrokViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
