package com.mehmet.screentracker;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class AnaSayfa extends AppCompatActivity {

    Button screenUsageButton,newtworkStatsButtons,raporlaButton;
    EditText mailEditText;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        screenUsageButton = findViewById(R.id.screenUsageStatsButton);
        screenUsageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ScreenUsageStatsMenu.class);
                startActivity(intent);
            }
        });

        newtworkStatsButtons = findViewById(R.id.newtworkStatsButtons);
        newtworkStatsButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), NetworkStatsMenu.class);
                startActivity(intent);
            }
        });

        raporlaButton = findViewById(R.id.raporlaButton);
        mailEditText = findViewById(R.id.mailEditText);

        arrayList = new ArrayList<>();

        UsageStats usageStatss;

        String PackageName = "Nothing";

        long TimeInforground = 500;

        int minutes = 500, seconds = 500, hours = 500;
        UsageStatsManager mUsageStatsManager = (UsageStatsManager) this.getSystemService(Context.USAGE_STATS_SERVICE);

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

                Log.i("MAILLOG", "PackageName is" + PackageName + "Time is: " + hours + "h" + ":" + minutes + "m" + seconds + "s");

                PackageName = PackageName.replace("com", "");
                PackageName = PackageName.replace(".", "");
                PackageName = PackageName.replace("android", "");
                PackageName = PackageName.replace("providers", "");
                PackageName = PackageName.replace("lge", "");
                PackageName = PackageName.replace("mehmet", "");
                PackageName = PackageName.replace("google", "");
                PackageName = PackageName.replace("google", "");
                PackageName = PackageName.replace("katana", "");
                PackageName = PackageName.replace("org", "");

                char isim='s';
                if(PackageName!=null && PackageName!=""){
                    isim=PackageName.toUpperCase().charAt(0);
                    PackageName=isim+""+PackageName.substring(1);
                }

                if(PackageName!="") {
                    arrayList.add(PackageName + " " + hours + " saat " + " " + minutes + " dakika " + " " + seconds + " saniye");
                    arrayList.add("\n");
                }

                raporlaButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mailGonder(mailEditText.getText().toString(),arrayList.toString());
                    }
                });

            }


        }
    }


    private void mailGonder(String adres,String icerik){

        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{adres});
        intent.putExtra(Intent.EXTRA_TEXT,icerik);

        startActivity(intent);


    }

}
