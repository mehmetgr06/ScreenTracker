package com.mehmet.screentracker;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.TrafficStats;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by MEHMET on 22.04.2019.
 */

public class Fragment_BaglantiBilgileri extends Fragment {

    TextView newtworkBilgiTextView,newtworkBilgiTextView2;
    ArrayList<String> liste;
    ArrayAdapter adapter;
    ListView listView;

    public static Fragment_BaglantiBilgileri fragmentBaglantiBilgileri(){

        return new Fragment_BaglantiBilgileri();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_baglantibilgileri,container,false);

        newtworkBilgiTextView=view.findViewById(R.id.newtworkBilgiTextView);
        newtworkBilgiTextView2=view.findViewById(R.id.newtworkBilgiTextView2);

        final String DEBUG_TAG = "NetworkStatusExample";

        ConnectivityManager connMgr =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        boolean isMobileConn = false;
        for (Network network : connMgr.getAllNetworks()) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn |= networkInfo.isConnected();
                newtworkBilgiTextView.setText("İnternete Wifi ile Bağlanmaktasınız");
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn |= networkInfo.isConnected();
                newtworkBilgiTextView.setText("İnternete Mobil Veri ile Bağlanmaktasınız");
            }


        }

        if(newtworkBilgiTextView.getText().equals("")){
            newtworkBilgiTextView.setText("İnternete Bağlı Değilsiniz");
        }

        Log.d(DEBUG_TAG, "Wifi connected: " + isWifiConn);
        Log.d(DEBUG_TAG, "Mobile connected: " + isMobileConn);

        String info = "";

        info += "Mobile Interface:\n";
        info += ("\tAlınan Veri: " + TrafficStats.getMobileRxBytes() + " bytes / " + TrafficStats.getMobileRxPackets() + " packets\n");
        info += ("\tGönderilen Veri: " + TrafficStats.getMobileTxBytes() + " bytes / " + TrafficStats.getMobileTxPackets() + " packets\n");

        info += "All Network Interface:\n";
        info += ("\tAlınan Veri: " + TrafficStats.getTotalRxBytes() + " bytes / " + TrafficStats.getTotalRxPackets() + " packets\n");
        info += ("\tGönderilen Veri: " + TrafficStats.getTotalTxBytes() + " bytes / " + TrafficStats.getTotalTxPackets() + " packets\n");

        newtworkBilgiTextView2.setText(info);

        liste=new ArrayList<>();
        listView=view.findViewById(R.id.networkStatsListView);

        ActivityManager manager = (ActivityManager) getContext().getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningApp : runningApps) {
            long received = TrafficStats.getUidRxBytes(runningApp.uid);
            long sent = TrafficStats.getUidTxBytes(runningApp.uid);
            Log.d("ss", String.format(Locale.getDefault(),
                    "uid: %1d - name: %s: Sent = %1d, Rcvd = %1d", runningApp.uid, runningApp.processName, sent, received));

            liste.add(runningApp.processName+" "+sent+" "+received);
        }

        adapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,liste);
        listView.setAdapter(adapter);

        return view;
    }



}
