package com.litosh.ilya.cubingtimeproj.myprofilefragment.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.views.UserInfoTextViewsView;

/**
 * UserInfoTextViewsPresenter
 *
 * Created by ilya_ on 21.06.2018.
 */

@InjectViewState
public class UserInfoTextViewsPresenter extends MvpPresenter<UserInfoTextViewsView> {

    public void initUserInfo() {
        ApiService.getUser(new UserCookie(), UserCookie.getCtUserId(), user -> {
            getViewState().initUserInfoTextViews(user);
        });
    }

}
