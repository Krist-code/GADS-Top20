package com.example.gads_top20;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.gads_top20.LearningLeaders;
import com.example.gads_top20.SkillLeaders;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int tabNum) {
        super(fm, tabNum);
        this.mNumOfTabs  = tabNum;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new LearningLeaders();
            case 1:
                return new SkillLeaders();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
