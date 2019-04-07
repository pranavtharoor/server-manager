package com.pranavtharoor.servermanager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pranavtharoor.servermanager.AccessTab;
import com.pranavtharoor.servermanager.RequestTab;
import com.pranavtharoor.servermanager.ServerTab;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int noOfTabs;

    public PagerAdapter(FragmentManager fm, int noOfTabs) {
        super(fm);
        this.noOfTabs = noOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ServerTab();
            case 1:
                return new RequestTab();
            case 2:
                return new AccessTab();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
