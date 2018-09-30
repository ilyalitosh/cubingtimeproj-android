package com.litosh.ilya.cubingtimeproj.myprofileactivity.presenters;

import com.litosh.ilya.ct_sdk.api.ProfileService;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;
import com.litosh.ilya.cubingtimeproj.myprofileactivity.views.UserProfileView;

/**
 * UserProfilePresenter
 *
 * @author Ilya Litosh
 */
public class UserProfilePresenter {

    private UserProfileView mUserProfileView;

    public UserProfilePresenter(UserProfileView userProfileView) {
        mUserProfileView = userProfileView;
    }

    /**
     * Инициализирует компоненты с данными о
     * пользователе, а также стену
     *
     */
    public void initUserProfile() {
        ProfileService profileService = new ProfileService(new UserCookie());
        profileService.getUserProfile(UserCookie.getCtUserId(), (user, wall) -> {
            mUserProfileView.initializeUserProfile(wall, user);
            mUserProfileView.hideProgressBar();
        });
    }

}
