package com.mehmet.screentracker;

import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Main2Activity extends AppCompatActivity {

    ListView listView;
    List liste;
    ArrayAdapter adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView=findViewById(R.id.listview2);
        arrayList=new ArrayList<>();

        if(Settings.ACTION_USAGE_ACCESS_SETTINGS ==null) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }

        String PackageName = "Nothing";
        long TimeInforground = 500;
        int minutes = 500, seconds = 500, hours = 500;

        UsageStatsManager mUsageStatsManager = (UsageStatsManager)getSystemService(USAGE_STATS_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        long start = calendar.getTimeInMillis();
        long end = System.currentTimeMillis();
        List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_WEEKLY, start, end);

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

