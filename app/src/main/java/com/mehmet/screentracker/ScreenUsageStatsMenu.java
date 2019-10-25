package com.mehmet.screentracker;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScreenUsageStatsMenu extends AppCompatActivity {

    Button anlikIstatistiklerButton,haftalikEkranKullanimiButton,akitfUygulamalarButton;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_usage_stats_menu);

      /*  anlikIstatistiklerButton=findViewById(R.id.anlikIstatistiklerButton);
        haftalikEkranKullanimiButton=findViewById(R.id.haftalikIstatitstiklerButton);
        akitfUygulamalarButton=findViewById(R.id.akitfUygulamalarButton);*/

        viewPager=findViewById(R.id.screenViewPager);
        tabLayout=findViewById(R.id.screenTabLayout);

        ScreenViewPagerAdaper adaper=new ScreenViewPagerAdaper(getSupportFragmentManager());
        viewPager.setAdapter(adaper);
        tabLayout.setupWithViewPager(viewPager);

      /*  anlikIstatistiklerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        haftalikEkranKullanimiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(intent);
            }
        });

        akitfUygulamalarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),Aktif_Uygulamalar.class);
                startActivity(intent);
            }
        });*/

    }
}
