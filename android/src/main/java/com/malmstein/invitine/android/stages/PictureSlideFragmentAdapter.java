package com.malmstein.invitine.android.stages;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.malmstein.invitine.android.fragments.PictureFragment;

public class PictureSlideFragmentAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 5;

    public PictureSlideFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new PictureFragment();
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}