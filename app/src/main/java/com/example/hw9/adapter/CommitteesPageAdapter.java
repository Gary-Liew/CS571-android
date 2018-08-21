package com.example.hw9.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hw9.fragment.CommitteesChildsFragment;

public class CommitteesPageAdapter extends FragmentPagerAdapter {

    private String[] titles = {"HOUSER", "SENATE", "JOINT"};

    private Context mContext;

    public CommitteesPageAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        String filter = "house";
        if (position == 1) {
            filter = "senate";
        } else if (position == 2) {
            filter = "joint";
        }

        return CommitteesChildsFragment.newInstance(filter, false);
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