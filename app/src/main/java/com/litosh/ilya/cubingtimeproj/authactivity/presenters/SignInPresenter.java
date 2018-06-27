package com.litosh.ilya.cubingtimeproj.authactivity.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.ct_sdk.callbacks.OnUserAuthorizateCallback;
import com.litosh.ilya.ct_sdk.models.Cookie;
import com.litosh.ilya.cubingtimeproj.authactivity.views.SignInButtonView;
import com.litosh.ilya.cubingtimeproj.db.DbService;
import com.litosh.ilya.cubingtimeproj.db.models.UserCache;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;

/**
 * SignInPresenter
 *
 * Created by ilya_ on 20.06.2018.
 */

@InjectViewState
public class SignInPresenter extends MvpPresenter<SignInButtonView> {

    private static final String TAG = "SignInPresenter";

    public void trySignIn() {
        UserCache userCache = new DbService().getUser();
        if (userCache != null) {
            if (userCache.isActive()) {
                getViewState().showProgressDialog();
                tryAuthorizate(userCache);
            }
        }
    }

    private void initUserCookie(Cookie cookie, String ctUserid) {
        UserCookie.setCbtl(cookie.getCbtl());
        UserCookie.setCbtp(cookie.getCbtp());
        UserCookie.setLang(cookie.getLang());
        UserCookie.setNight(cookie.getNight());
        UserCookie.setNoprev(cookie.getNoprev());
        UserCookie.setPhpSessId(cookie.getPhpSessId());
        UserCookie.setCtUserId(ctUserid);
    }

    private void tryAuthorizate(UserCache userCache) {
        ApiService.authorizate(
                userCache.getEmail(),
                userCache.getPass(),
                new OnUserAuthorizateCallback() {
                    @Override
                    public void onSuccess(Cookie cookie, String userId) {
                        initUserCookie(cookie, userId);
                        getViewState().hideProgressDialog();
                        getViewState().startProfileActivity();
                    }

                    @Override
                    public void onError(Throwable t, String errorMessage) {
                        updateUserCache(userCache);
                        getViewState().hideProgressDialog();
                        Log.i(TAG, t.toString() + ", " + errorMessage);
                    }
                });
    }

    private void updateUserCache(UserCache userCache) {
        userCache.setActive(false);
        new DbService().updateUser(userCache);
    }

}
