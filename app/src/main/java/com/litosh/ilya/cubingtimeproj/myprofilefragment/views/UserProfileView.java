package com.litosh.ilya.cubingtimeproj.myprofilefragment.views;

import com.arellomobile.mvp.MvpView;
import com.litosh.ilya.ct_sdk.models.profile.User;
import com.litosh.ilya.ct_sdk.models.profile.Wall;

/**
 * UserProfileView
 *
 * @author Ilya Litosh
 */
public interface UserProfileView extends MvpView {

    /**
     * Вызывается при инициализации профиля
     *
     * @param wall
     * @param user
     */
    void initializeUserProfile(Wall wall, User user);

    /**
     * Вызывается, чтобы убрать прогрессбар
     *
     */
    void hideProgressBar();

}
