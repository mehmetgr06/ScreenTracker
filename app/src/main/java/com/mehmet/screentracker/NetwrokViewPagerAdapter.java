package com.mehmet.screentracker;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by MEHMET on 21.04.2019.
 */

public class NetwrokViewPagerAdapter extends FragmentStatePagerAdapter {


    public NetwrokViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment selectedFragment;

        switch (i){
            case 0:
                selectedFragment=Fragment_PlayStore_Indirmeler.fragmentPlayStoreIndirmeler();
                break;
            case 1:
                selectedFragment=Fragment_TarayiciGecmisi.fragmentTarayiciGecmisi();
                break;
            case 2:
                selectedFragment=Fragment_BaglantiBilgileri.fragmentBaglantiBilgileri();
                break;
            default:
                return null;
        }

        return selectedFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        CharSequence selectedTitle;

        switch (position){
            case 0:
                selectedTitle="İndirilen Uygulamalar/Servisler";

                break;
            case 1:
                selectedTitle="İndirilen Dosyalar";
                break;

            case 2:
                selectedTitle="Bağlantı Bilgileri";
                break;
            default:
                return null;
        }


        return selectedTitle;
    }

}
