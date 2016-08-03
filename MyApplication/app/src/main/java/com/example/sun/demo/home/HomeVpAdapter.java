package com.example.sun.demo.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by sun on 16/8/2.
 */
public class HomeVpAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragementList;

    public HomeVpAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mFragementList = list;
    }



    @Override
    public Fragment getItem(int position) {
        return mFragementList.get(position);
    }

    @Override
    public int getCount() {
        return mFragementList == null ? 0 : mFragementList.size();
    }
}
