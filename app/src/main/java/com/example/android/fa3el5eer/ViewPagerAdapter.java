package com.example.android.fa3el5eer;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.fa3el5eer.donationRequestList.Fragment2;
import com.example.android.fa3el5eer.postsList.Fragment1;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    Fragment [] fragments ={new Fragment1(),new Fragment2()};

    String title;


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

                    return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
               title = "المقالات";
               break;
            case 1:
                title =  "طلبات التبرع";
                break;

        }
        return title;
    }
}
