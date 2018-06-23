package com.litosh.ilya.cubingtimeproj.baseactivity.models;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatImageView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * ActionBarDrawerData
 *
 * Created by ilya_ on 24.06.2018.
 */

public class ActionBarDrawerData {

    private Context mContext;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private AppCompatImageView mPointer;
    private RelativeLayout mPointerSpace;

    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    public void setDrawerLayout(DrawerLayout mDrawerLayout) {
        this.mDrawerLayout = mDrawerLayout;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public NavigationView getNavigationView() {
        return mNavigationView;
    }

    public void setNavigationView(NavigationView mNavigationView) {
        this.mNavigationView = mNavigationView;
    }

    public AppCompatImageView getPointer() {
        return mPointer;
    }

    public void setPointer(AppCompatImageView mPointer) {
        this.mPointer = mPointer;
    }

    public RelativeLayout getPointerSpace() {
        return mPointerSpace;
    }

    public void setPointerSpace(RelativeLayout mPointerSpace) {
        this.mPointerSpace = mPointerSpace;
    }

}
