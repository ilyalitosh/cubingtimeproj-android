package com.litosh.ilya.cubingtimeproj.myprofilefragment.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.views.UserProfileView;

/**
 * UserProfilePresenter
 *
 * @author Ilya Litosh
 */
@InjectViewState
public class UserProfilePresenter extends MvpPresenter<UserProfileView> {

    /**
     * Инициализирует компоненты с данными о
     * пользователе, а также стену
     *
     */
    public void initUserProfile() {
        ApiService.getUserProfile(new UserCookie(), UserCookie.getCtUserId(), (user, wall) -> {
            getViewState().initializeUserInfoTextViews(user);
            getViewState().initializeUserWall(wall);
        });
    }

}
