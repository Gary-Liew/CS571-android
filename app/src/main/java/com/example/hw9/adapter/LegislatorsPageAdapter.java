package com.example.hw9.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hw9.fragment.LegislatorsChildsFragment;

public class LegislatorsPageAdapter extends FragmentPagerAdapter {

    private String[] titles = {"BY STATES", "HOUSE", "SENATE"};

    private Context mContext;

    public LegislatorsPageAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        String filter = "";
        if (position == 1) {
            filter = "house";
        } else if (position == 2) {
            filter = "senate";
        }

        return LegislatorsChildsFragment.newInstance(filter, false);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}