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
     * Вызывается при для инициализации информации о юзере
     *
     * @param user юзер
     */
    void initializeUserInfoTextViews(User user);

    /**
     * Вызывается при инициализации стены пользователя
     *
     * @param wall сущность стены пользователя
     */
    void initializeUserWall(Wall wall);

}
