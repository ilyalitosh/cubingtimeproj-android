package com.litosh.ilya.cubingtimeproj.myprofilefragment.views;

import com.arellomobile.mvp.MvpView;
import com.litosh.ilya.ct_sdk.models.User;

/**
 * UserInfoTextViewsView
 *
 * Created by ilya_ on 21.06.2018.
 */

public interface UserInfoTextViewsView extends MvpView {

    /**
     * Вызывается при для инициализации информации о юзере
     *
     * @param user юзер
     */
    void initUserInfoTextViews(User user);

}
