package com.litosh.ilya.cubingtimeproj.baseactivity.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.widget.RelativeLayout;

import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.baseactivity.models.ActionBarDrawerData;
import com.litosh.ilya.cubingtimeproj.baseactivity.presenters.NavigationViewPresenter;
import com.litosh.ilya.cubingtimeproj.baseactivity.views.NavigationViewView;
import com.litosh.ilya.cubingtimeproj.mymessagesactivity.ui.MyMessagesActivity;
import com.litosh.ilya.cubingtimeproj.myprofileactivity.ui.MyProfileActivity;
import com.litosh.ilya.cubingtimeproj.timeractivity.ui.MyTimerActivity;

/**
 * BaseActivity
 * Базовая активность с дефолтной логикой
 * навигационного меню
 *
 * @author Ilya Litosh
 */
public abstract class BaseActivity extends AppCompatActivity implements NavigationViewView {

    public static final int REQUEST_CODE_LIKES_CHANGES = 1001;
    private NavigationViewPresenter mNavigationViewPresenter;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private RelativeLayout mPointerSpace;
    private AppCompatImageView mPointer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initActivityComponents();
        initActivityListeners();

    }

    public void initActivityComponents() {
        mDrawerLayout = findViewById(R.id.activity_base_drawer_layout);
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mNavigationView = findViewById(R.id.activity_base_navigation_view);
        mPointerSpace = findViewById(R.id.activity_base_pointer_space);
        mPointer = findViewById(R.id.activity_base_pointer);
        mNavigationViewPresenter = new NavigationViewPresenter(this);
        //mFragmentContainer = findViewById(R.id.activity_base_fragment_container);
        initMenuIcons();
    }

    private void initMenuIcons() {
        AppCompatImageView profileIcon = new AppCompatImageView(this);
        profileIcon.setImageResource(R.drawable.ic_my_profile);
        mNavigationView.getMenu().getItem(0).setActionView(profileIcon);
        AppCompatImageView myMessagesIcon = new AppCompatImageView(this);
        myMessagesIcon.setImageResource(R.drawable.ic_my_messages);
        mNavigationView.getMenu().getItem(1).setActionView(myMessagesIcon);
        AppCompatImageView myTimerIcon = new AppCompatImageView(this);
        myTimerIcon.setImageResource(R.drawable.ic_my_timer);
        mNavigationView.getMenu().getItem(2).setActionView(myTimerIcon);
    }

    public void initActivityListeners() {
        mPointerSpace.setOnClickListener(v -> {
            mDrawerLayout.openDrawer(GravityCompat.START);
        });
        mNavigationView.setNavigationItemSelectedListener(item -> {
            //mDrawerLayout.closeDrawer(GravityCompat.START);
            switch (item.getItemId()) {
                case R.id.navigation_menu_my_profile:
                    Intent intentMyProfile =
                            new Intent(this, MyProfileActivity.class);
                    intentMyProfile.setFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentMyProfile);
                    overridePendingTransition(R.anim.anim_activity_show, R.anim.anim_activity_hide);
                    break;
                case R.id.navigation_menu_my_messages:
                    Intent intentMyMessages =
                            new Intent(this, MyMessagesActivity.class);
                    intentMyMessages.setFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentMyMessages);
                    overridePendingTransition(R.anim.anim_activity_show, R.anim.anim_activity_hide);
                    break;
                case R.id.navigation_menu_my_timer:
                    Intent intentMyTimer =
                            new Intent(this, MyTimerActivity.class);
                    intentMyTimer.setFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentMyTimer);
                    overridePendingTransition(R.anim.anim_activity_show, R.anim.anim_activity_hide);
                    break;
            }
            return false;
        });
        mNavigationViewPresenter.setDrawerListener(getActionBarDrawerData());
    }

    private ActionBarDrawerData getActionBarDrawerData() {
        ActionBarDrawerData actionBarDrawerData = new ActionBarDrawerData();
        actionBarDrawerData.setContext(this);
        actionBarDrawerData.setDrawerLayout(mDrawerLayout);
        actionBarDrawerData.setNavigationView(mNavigationView);
        actionBarDrawerData.setPointer(mPointer);
        actionBarDrawerData.setPointerSpace(mPointerSpace);
        return actionBarDrawerData;
    }

    @Override
    public void setDrawerListener(ActionBarDrawerToggle actionBarDrawerToggle) {
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @LayoutRes
    protected abstract int getLayoutId();

}
