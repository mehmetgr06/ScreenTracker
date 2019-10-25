package com.mehmet.screentracker;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Browser;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MEHMET on 21.04.2019.
 */

public class Fragment_TarayiciGecmisi extends Fragment {

    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> arrayList;
    TextView indirilenlerTextView;

    public static Fragment_TarayiciGecmisi fragmentTarayiciGecmisi(){
        return new Fragment_TarayiciGecmisi();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_tarayivi_gecmisi,container,false);

        indirilenlerTextView=rootView.findViewById(R.id.indirilenlerTextView);
        indirilenlerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
            }
        });


        return rootView;
    }
}
