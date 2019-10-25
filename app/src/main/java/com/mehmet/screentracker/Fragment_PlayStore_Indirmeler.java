package com.mehmet.screentracker;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
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

import static android.content.ContentValues.TAG;

/**
 * Created by MEHMET on 21.04.2019.
 */

public class Fragment_PlayStore_Indirmeler extends Fragment {

    ArrayAdapter adapter;
    ArrayList<String> arrayList;


    public static Fragment_PlayStore_Indirmeler fragmentPlayStoreIndirmeler(){

        return new Fragment_PlayStore_Indirmeler();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_playstore_indirmeler,container,false);

        ListView listView=rootView.findViewById(R.id.playStoreindirilenlerListview);
        arrayList = new ArrayList<>();

        final PackageManager pm = getContext().getPackageManager();
//get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            Log.d(TAG, "Installed package :" + packageInfo.packageName);
            Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
            Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));

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

                arrayList.add(packageInfo.packageName);

                Collections.sort(arrayList);
            }

        }

        adapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

        return rootView;
    }
}
