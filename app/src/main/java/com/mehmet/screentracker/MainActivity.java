package com.mehmet.screentracker;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.provider.Browser;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class MainActivity extends  AppCompatActivity  {

    ListView listView;
    List liste;
    ArrayAdapter adapter;
    ArrayList<String> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Settings.ACTION_USAGE_ACCESS_SETTINGS ==null) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }



        listView=findViewById(R.id.uygulamalarListview);
        arrayList=new ArrayList<>();


        UsageStats usageStatss;

        String PackageName = "Nothing";

        long TimeInforground = 500;

        int minutes = 500, seconds = 500, hours = 500;
        UsageStatsManager mUsageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);

        Log.i("mUsageStatsManager",mUsageStatsManager+"");

        long time = System.currentTimeMillis();

        assert mUsageStatsManager != null;
        List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, time - 1000 * 10, time);

        Log.i("statsdene",stats+"");

        if (stats != null) {
            SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
            for (UsageStats usageStats : stats) {

                TimeInforground = usageStats.getTotalTimeInForeground();

                PackageName = usageStats.getPackageName();

                minutes = (int) ((TimeInforground / (1000 * 60)) % 60);

                seconds = (int) (TimeInforground / 1000) % 60;

                hours = (int) ((TimeInforground / (1000 * 60 * 60)) % 24);

                Log.i("BAC", "PackageName is" + PackageName + "Time is: " + hours + "h" + ":" + minutes + "m" + seconds + "s");

                arrayList.add(PackageName+" "+hours+" saat "+" "+minutes+" dakika "+" "+seconds+" saniye");

            }

            

            adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(adapter);

        }
    }


}

