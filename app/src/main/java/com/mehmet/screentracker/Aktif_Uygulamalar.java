package com.mehmet.screentracker;

import android.annotation.SuppressLint;
import android.app.usage.UsageStatsManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Aktif_Uygulamalar extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> arrayList;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktif__uygulamalar);

        listView=findViewById(R.id.listviewAktif);
        arrayList=new ArrayList<>();

        final PackageManager pm = getPackageManager();
        //get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        UsageStatsManager mUsageStatsManager = (UsageStatsManager)getSystemService(USAGE_STATS_SERVICE);

        for (ApplicationInfo packageInfo : packages) {
            arrayList.add(packageInfo.packageName+" "+mUsageStatsManager.isAppInactive(packageInfo.packageName));


        }



        adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

    }
}
