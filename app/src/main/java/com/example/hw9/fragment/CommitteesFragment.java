package com.example.hw9.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hw9.R;
import com.example.hw9.adapter.CommitteesPageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommitteesFragment extends Fragment {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_committees, container, false);
        ButterKnife.bind(this, view);

        CommitteesPageAdapter adapter = new CommitteesPageAdapter(getChildFragmentManager(), getActivity());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

}
