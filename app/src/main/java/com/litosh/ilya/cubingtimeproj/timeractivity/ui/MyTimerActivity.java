package com.litosh.ilya.cubingtimeproj.timeractivity.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.baseactivity.ui.BaseActivity;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.TimerData;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.adapters.TimerViewPagerAdapter;

/**
 * MyTimerActivity
 *
 * @author Ilya Litosh
 */
public class MyTimerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_timer;
    }

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TimerViewPagerAdapter mTimerViewPagerAdapter;
    @Override
    public void initActivityComponents() {
        super.initActivityComponents();
        mTabLayout = findViewById(R.id.activity_timer_tablayout);
        mViewPager = findViewById(R.id.activity_timer_viewpager);
        mTimerViewPagerAdapter = new TimerViewPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mTimerViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(TimerData.TIMER_POSITION).setCustomView(R.layout.activity_timer_tablayout_tabitem_timer);
        mTabLayout.getTabAt(TimerData.SOLVES_POSITION).setCustomView(R.layout.activity_timer_tablayout_tabitem_solves);
        mTabLayout.getTabAt(TimerData.STATS_POSITION).setCustomView(R.layout.activity_timer_tablayout_tabitem_stats);
    }

    public TimerViewPagerAdapter getTimerViewPagerAdapter() {
        return mTimerViewPagerAdapter;
    }

    @Override
    public void initActivityListeners() {
        super.initActivityListeners();
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case TimerData.TIMER_POSITION:
                        tab.getCustomView()
                                .findViewById(R.id.activity_timer_tablayout_tabitem_timer_image)
                                .setBackgroundResource(R.drawable.ic_timer_selected);
                        break;
                    case TimerData.STATS_POSITION:
                        tab.getCustomView()
                                .findViewById(R.id.activity_timer_tablayout_tabitem_stats_image)
                                .setBackgroundResource(R.drawable.ic_stats_selected);
                        break;
                    case TimerData.SOLVES_POSITION:
                        tab.getCustomView()
                                .findViewById(R.id.activity_timer_tablayout_tabitem_solves_image)
                                .setBackgroundResource(R.drawable.ic_solves_selected);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case TimerData.TIMER_POSITION:
                        tab.getCustomView()
                                .findViewById(R.id.activity_timer_tablayout_tabitem_timer_image)
                                .setBackgroundResource(R.drawable.ic_timer);
                        break;
                    case TimerData.STATS_POSITION:
                        tab.getCustomView()
                                .findViewById(R.id.activity_timer_tablayout_tabitem_stats_image)
                                .setBackgroundResource(R.drawable.ic_stats);
                        break;
                    case TimerData.SOLVES_POSITION:
                        tab.getCustomView()
                                .findViewById(R.id.activity_timer_tablayout_tabitem_solves_image)
                                .setBackgroundResource(R.drawable.ic_solves);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}
