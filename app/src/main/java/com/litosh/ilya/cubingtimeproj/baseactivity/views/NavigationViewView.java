package com.litosh.ilya.cubingtimeproj.baseactivity.views;

import android.support.v7.app.ActionBarDrawerToggle;

import com.arellomobile.mvp.MvpView;

/**
 * NavigationViewView
 *
 * Created by ilya_ on 24.06.2018.
 */

public interface NavigationViewView extends MvpView {

    /**
     * Вызывается при установке drawer listener
     *
     * @param actionBarDrawerToggle actionBarDrawerToggle
     */
    void setDrawerListener(ActionBarDrawerToggle actionBarDrawerToggle);

}
