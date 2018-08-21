package com.example.hw9.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hw9.fragment.BillsChildsFragment;
import com.example.hw9.fragment.CommitteesChildsFragment;
import com.example.hw9.fragment.LegislatorsChildsFragment;

public class FavoritesPageAdapter extends FragmentPagerAdapter {

    private String[] titles = {"LEGISLATORS", "BILLS", "COMMITTEES"};

    private Context mContext;

    public FavoritesPageAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;

    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return LegislatorsChildsFragment.newInstance("true", true);
        } else if (position == 1) {
            return BillsChildsFragment.newInstance("true", true);
        } else {
            return CommitteesChildsFragment.newInstance("true", true);
        }

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