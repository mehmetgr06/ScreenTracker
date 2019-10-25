package com.mehmet.screentracker;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by MEHMET on 21.04.2019.
 */

public class Fragment_Gunluk extends Fragment {

    ListView listView;
    List liste;
    ArrayAdapter adapter;
    ArrayList<String> arrayList;

    public static Fragment_Gunluk fragment_gunluk(){
        return new Fragment_Gunluk();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gunluk, container, false);

        if (Settings.ACTION_USAGE_ACCESS_SETTINGS == null) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }

        listView = rootView.findViewById(R.id.uygulamalarListview);
        arrayList = new ArrayList<>();

        UsageStats usageStatss;

        String PackageName = "Nothing";

        long TimeInforground = 500;

        int minutes = 500, seconds = 500, hours = 500;
        UsageStatsManager mUsageStatsManager = (UsageStatsManager) getContext().getSystemService(Context.USAGE_STATS_SERVICE);

        Log.i("mUsageStatsManager", mUsageStatsManager + "");

        long time = System.currentTimeMillis();

        assert mUsageStatsManager != null;
        List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, time - 1000 * 10, time);

        Log.i("statsdene", stats + "");

        if (stats != null) {
            SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
            for (UsageStats usageStats : stats) {

                TimeInforground = usageStats.getTotalTimeInForeground();

                PackageName = usageStats.getPackageName();

                minutes = (int) ((TimeInforground / (1000 * 60)) % 60);

                seconds = (int) (TimeInforground / 1000) % 60;

                hours = (int) ((TimeInforground / (1000 * 60 * 60)) % 24);

                Log.i("BAC", "PackageName is" + PackageName + "Time is: " + hours + "h" + ":" + minutes + "m" + seconds + "s");

                PackageName=PackageName.replace("com","");
                PackageName=PackageName.replace(".","");
                PackageName=PackageName.replace("android","");
                PackageName=PackageName.replace("providers","");
                PackageName=PackageName.replace("lge","");
                PackageName=PackageName.replace("mehmet","");
                PackageName=PackageName.replace("google","");
                PackageName=PackageName.replace("google","");
                PackageName=PackageName.replace("katana","");
                PackageName=PackageName.replace("org","");

                char isim='s';
                if(PackageName!=null && PackageName!=""){
                    isim=PackageName.toUpperCase().charAt(0);
                    PackageName=isim+""+PackageName.substring(1);
                }



                if(PackageName!=null &&PackageName!="") {
                    arrayList.add(PackageName + " " + hours + " saat " + " " + minutes + " dakika " + " " + seconds + " saniye");

                    Collections.sort(arrayList);
                }

            }

            adapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(adapter);

            return rootView;
        }


        return rootView;
    }}
