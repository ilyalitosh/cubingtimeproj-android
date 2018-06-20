package com.litosh.ilya.cubingtimeproj.authactivity.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.ct_sdk.callbacks.OnUserAuthorizateListener;
import com.litosh.ilya.ct_sdk.models.Cookie;
import com.litosh.ilya.cubingtimeproj.authactivity.views.SignInButtonView;
import com.litosh.ilya.cubingtimeproj.db.DbService;
import com.litosh.ilya.cubingtimeproj.db.models.User;
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
        User user = new DbService().getUser();
        if (user != null) {
            if (user.isActive()) {
                getViewState().showProgressDialog();
                tryAuthorizate(user);
            }
        }
    }

    private void initUserCookie(Cookie cookie) {
        UserCookie.setCbtl(cookie.getCbtl());
        UserCookie.setCbtp(cookie.getCbtp());
        UserCookie.setLang(cookie.getLang());
        UserCookie.setNight(cookie.getNight());
        UserCookie.setNoprev(cookie.getNoprev());
        UserCookie.setPhpSessId(cookie.getPhpSessId());
    }

    private void tryAuthorizate(User user) {
        ApiService.authorizate(
                user.getEmail(),
                user.getPass(),
                new OnUserAuthorizateListener() {
                    @Override
                    public void onSuccess(Cookie cookie, String userId) {
                        initUserCookie(cookie);
                        getViewState().hideProgressDialog();
                        getViewState().startProfileActivity();
                    }

                    @Override
                    public void onError(Throwable t, String errorMessage) {
                        user.setActive(false);
                        new DbService().deleteUser();
                        getViewState().hideProgressDialog();
                        Log.i(TAG, t.toString() + ", " + errorMessage);
                    }
                });
    }

}
