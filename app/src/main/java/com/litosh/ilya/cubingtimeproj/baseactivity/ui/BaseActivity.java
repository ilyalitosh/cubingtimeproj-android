package com.litosh.ilya.cubingtimeproj.baseactivity.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatImageView;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.baseactivity.models.ActionBarDrawerData;
import com.litosh.ilya.cubingtimeproj.baseactivity.presenters.NavigationViewPresenter;
import com.litosh.ilya.cubingtimeproj.baseactivity.views.NavigationViewView;
import com.litosh.ilya.cubingtimeproj.mymessagesactivity.ui.MyMessagesActivity;
import com.litosh.ilya.cubingtimeproj.myprofileactivity.ui.MyProfileActivity;

/**
 * BaseActivity
 *
 * Created by ilya_ on 24.06.2018.
 */

public class BaseActivity extends MvpAppCompatActivity implements NavigationViewView {

    @InjectPresenter
    NavigationViewPresenter mNavigationViewPresenter;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private RelativeLayout mPointerSpace;
    private AppCompatImageView mPointer;
    private RelativeLayout mFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initActivityComponents();
        initActivityListeners();

    }

    public void initActivityComponents() {
        mDrawerLayout = findViewById(R.id.activity_base_drawer_layout);
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mNavigationView = findViewById(R.id.activity_base_navigation_view);
        mPointerSpace = findViewById(R.id.activity_base_pointer_space);
        mPointer = findViewById(R.id.activity_base_pointer);
        mFragmentContainer = findViewById(R.id.activity_base_fragment_container);
    }

    public void initActivityListeners() {
        mPointerSpace.setOnClickListener(v -> {
            mDrawerLayout.openDrawer(GravityCompat.START);
        });
        mNavigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_menu_my_profile:
                    Intent intentMyProfile =
                            new Intent(this, MyProfileActivity.class);
                    intentMyProfile.setFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentMyProfile);
                    break;
                case R.id.navigation_menu_my_messages:
                    Intent intentMyMessages =
                            new Intent(this, MyMessagesActivity.class);
                    intentMyMessages.setFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentMyMessages);
                    break;
            }
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return false;
        });
        mNavigationViewPresenter.setDrawerListener(getActionBarDrawerData());
    }

    public RelativeLayout getFragmentContainer() {
        return mFragmentContainer;
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
}
