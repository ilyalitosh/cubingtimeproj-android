package com.litosh.ilya.cubingtimeproj.baseactivity.views;

import android.support.v7.app.ActionBarDrawerToggle;

/**
 * NavigationViewView
 *
 * @author Ilya Litosh
 */
public interface NavigationViewView {

    /**
     * Вызывается при установке drawer listener
     *
     * @param actionBarDrawerToggle actionBarDrawerToggle
     */
    void setDrawerListener(ActionBarDrawerToggle actionBarDrawerToggle);

}
