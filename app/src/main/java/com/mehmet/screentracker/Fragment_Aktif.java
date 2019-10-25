package com.mehmet.screentracker;

import android.annotation.SuppressLint;
import android.app.usage.UsageStatsManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.Context.USAGE_STATS_SERVICE;

/**
 * Created by MEHMET on 21.04.2019.
 */

public class Fragment_Aktif extends Fragment {

    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> arrayList;

    public static Fragment_Aktif fragmentAktif(){
        return new Fragment_Aktif();
    }

    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_aktif,container,false);

        listView=rootView.findViewById(R.id.listviewAktif);
        arrayList=new ArrayList<>();

        final PackageManager pm = getContext().getPackageManager();
        //get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        UsageStatsManager mUsageStatsManager = (UsageStatsManager)getContext().getSystemService(USAGE_STATS_SERVICE);

        for (ApplicationInfo packageInfo : packages) {

            packageInfo.packageName=packageInfo.packageName.replace("com","");
            packageInfo.packageName=packageInfo.packageName.replace(".","");
            packageInfo.packageName=packageInfo.packageName.replace("android","");
            packageInfo.packageName=packageInfo.packageName.replace("providers","");
            packageInfo.packageName=packageInfo.packageName.replace("lge","");
            packageInfo.packageName=packageInfo.packageName.replace("mehmet","");
            packageInfo.packageName=packageInfo.packageName.replace("google","");
            packageInfo.packageName=packageInfo.packageName.replace("google","");
            packageInfo.packageName=packageInfo.packageName.replace("katana","");
            packageInfo.packageName=packageInfo.packageName.replace("org","");

            char isim='s';
            if(packageInfo.packageName!=null && packageInfo.packageName!=""){
                isim=packageInfo.packageName.toUpperCase().charAt(0);
                packageInfo.packageName=isim+""+packageInfo.packageName.substring(1);
            }

            if(packageInfo.packageName!=""){

                arrayList.add(packageInfo.packageName+" --> "+mUsageStatsManager.isAppInactive(packageInfo.packageName));

                Collections.sort(arrayList);
            }


        }



        adapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);


        return rootView;
    }
}
