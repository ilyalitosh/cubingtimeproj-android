package com.litosh.ilya.cubingtimeproj.timeractivity.models.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.litosh.ilya.cubingtimeproj.timeractivity.models.TimerData;
import com.litosh.ilya.cubingtimeproj.timeractivity.ui.SolvesFragment;
import com.litosh.ilya.cubingtimeproj.timeractivity.ui.TimerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * TimerViewPagerAdapter
 *
 * @author Ilya Litosh
 */
public class TimerViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mPages;
    private LayoutInflater mLayoutInflater;
    private FragmentManager mFragmentManager;

    public TimerViewPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        mFragmentManager = fragmentManager;
        mLayoutInflater = LayoutInflater.from(context);
        initializePages();
    }

    private void initializePages() {
        mPages = new ArrayList<>();
        mPages.add(new TimerFragment());
        mPages.add(new SolvesFragment());
        mPages.add(new TimerFragment());
    }

    @Override
    public int getCount() {
        return mPages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object fragment) {
        return ((Fragment) fragment).getView() == view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mFragmentManager.beginTransaction().remove(mPages.get(position)).commit();
        //mPages.removeSolve(position);
    }

    @Override
    public Fragment instantiateItem(ViewGroup container, int position){
        Fragment fragment = getItem(position);
        FragmentTransaction trans = mFragmentManager.beginTransaction();
        trans.add(container.getId(), fragment, "fragment:" + position);
        trans.commit();
        return fragment;
    }

    @Override
    public Fragment getItem(int position) {
        if (position < mPages.size() && position >= 0) {
            return mPages.get(position);
        } else if (position < 0) {
            throw new IllegalStateException("Min position can be not smaller than 0");
        } else {
            throw new IllegalStateException("Max position can be not higher than " + (mPages.size() - 1));
        }
    }

    public Fragment getFragment(int position) {
        return mPages.get(position);
    }

}
