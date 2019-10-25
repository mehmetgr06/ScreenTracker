package com.mehmet.screentracker;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by MEHMET on 21.04.2019.
 */

public class ScreenViewPagerAdaper extends FragmentStatePagerAdapter {

    public ScreenViewPagerAdaper(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        Fragment selectedFragment;

        switch (i){
            case 0:
                selectedFragment=Fragment_Gunluk.fragment_gunluk();
                break;
            case 1:
                selectedFragment=Fragment_Haftalik.fragmentHaftalik(); break;
            case 2:
                selectedFragment=Fragment_Aktif.fragmentAktif(); break;
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
                selectedTitle="Günlük";
                break;
            case 1:
                selectedTitle="Haftalık"; break;
            case 2:
                selectedTitle="Aktif/Pasif"; break;
            default:
                return null;
        }


        return selectedTitle;
    }
}
