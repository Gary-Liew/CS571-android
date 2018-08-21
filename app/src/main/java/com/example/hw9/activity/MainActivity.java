package com.example.hw9.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hw9.R;
import com.example.hw9.fragment.BillsFragment;
import com.example.hw9.fragment.CommitteesFragment;
import com.example.hw9.fragment.FavoritesFragment;
import com.example.hw9.fragment.LegislatorsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String NAV_LEGISLATORS = "nav_legislators";
    public static final String NAV_BILLS = "nav_bills";
    public static final String NAV_COMMITTEES = "nav_committees";
    public static final String NAV_FAVORITES = "nav_favorites";
    private LegislatorsFragment mLegislatorsFragment;
    private BillsFragment mBillsFragment;
    private CommitteesFragment mCommitteesFragment;
    private FavoritesFragment mFavoritesFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initFragment(savedInstanceState);
        showContent(NAV_LEGISLATORS);
    }

    private void initFragment(Bundle savedInstanceState) {
        mFragmentManager = this.getSupportFragmentManager();
        if (savedInstanceState != null) {
            mLegislatorsFragment = (LegislatorsFragment) mFragmentManager.findFragmentByTag(NAV_LEGISLATORS);
            mBillsFragment = (BillsFragment) mFragmentManager.findFragmentByTag(NAV_BILLS);
            mCommitteesFragment = (CommitteesFragment) mFragmentManager.findFragmentByTag(NAV_COMMITTEES);
            mFavoritesFragment = (FavoritesFragment) mFragmentManager.findFragmentByTag(NAV_FAVORITES);
        }
        if (mLegislatorsFragment == null) {
            mLegislatorsFragment = new LegislatorsFragment();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mLegislatorsFragment, NAV_LEGISLATORS).commit();
        }
        if (mBillsFragment == null) {
            mBillsFragment = new BillsFragment();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mBillsFragment, NAV_BILLS).commit();
        }
        if (mCommitteesFragment == null) {
            mCommitteesFragment = new CommitteesFragment();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mCommitteesFragment, NAV_COMMITTEES).commit();
        }

        if (mFavoritesFragment == null) {
            mFavoritesFragment = new FavoritesFragment();
            mFragmentManager.beginTransaction()
                    .add(R.id.vf_container, mFavoritesFragment, NAV_FAVORITES).commit();
        }

    }

    private void showContent(String tag) {

        if (tag.equals(NAV_LEGISLATORS)) {
            mFragmentManager.beginTransaction().show(mLegislatorsFragment).hide(mBillsFragment).hide(mCommitteesFragment).hide(mFavoritesFragment).commit();
        } else if (tag.equals(NAV_BILLS)) {
            mFragmentManager.beginTransaction().hide(mLegislatorsFragment).show(mBillsFragment).hide(mCommitteesFragment).hide(mFavoritesFragment).commit();
        } else if (tag.equals(NAV_COMMITTEES)) {
            mFragmentManager.beginTransaction().hide(mLegislatorsFragment).hide(mBillsFragment).show(mCommitteesFragment).hide(mFavoritesFragment).commit();
        } else if (tag.equals(NAV_FAVORITES)) {
            mFragmentManager.beginTransaction().hide(mLegislatorsFragment).hide(mBillsFragment).hide(mCommitteesFragment).show(mFavoritesFragment).commit();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_legislators) {

            showContent(NAV_LEGISLATORS);
            setTitle(item.getTitle());

        } else if (id == R.id.nav_bills) {

            showContent(NAV_BILLS);
            setTitle(item.getTitle());

        } else if (id == R.id.nav_committees) {

            showContent(NAV_COMMITTEES);
            setTitle(item.getTitle());

        } else if (id == R.id.nav_favorites) {

            showContent(NAV_FAVORITES);
            setTitle(item.getTitle());

        } else if (id == R.id.nav_about) {

            startActivity(new Intent(this, AboutMeActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
