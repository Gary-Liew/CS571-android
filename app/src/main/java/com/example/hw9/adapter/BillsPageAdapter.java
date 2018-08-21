package com.example.hw9.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hw9.fragment.BillsChildsFragment;

public class BillsPageAdapter extends FragmentPagerAdapter {

    private String[] titles = {"Active Bills", "New Bills"};

    private Context mContext;

    public BillsPageAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;

    }

    @Override
    public Fragment getItem(int position) {
        String filter = "true";
        if (position == 1) {
            filter = "";
        }
        return BillsChildsFragment.newInstance(filter, false);
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