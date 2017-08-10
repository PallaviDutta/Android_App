package com.example.userpc.android_app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by user pc on 10-08-2017.
 */
public class Adapter extends FragmentPagerAdapter {

    String titles[]={"Actions","Show List"};

    public Adapter(FragmentManager manager)
    {
        super(manager);
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos)
        {
            case 0:
                return new Actions();
            case 1:
                return new ShowList();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int pos)
    {
        return titles[pos];
    }
}
