package com.litosh.ilya.cubingtimeproj.authactivity.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.ct_sdk.callbacks.OnUserAuthorizateListener;
import com.litosh.ilya.ct_sdk.models.Cookie;
import com.litosh.ilya.cubingtimeproj.authactivity.models.UserSignInModel;
import com.litosh.ilya.cubingtimeproj.authactivity.views.SignInButtonView;
import com.litosh.ilya.cubingtimeproj.db.DbService;
import com.litosh.ilya.cubingtimeproj.db.models.UserCache;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;

/**
 * SignInButtonPresenter
 *
 * Created by ilya_ on 19.06.2018.
 */

@InjectViewState
public class SignInButtonPresenter extends MvpPresenter<SignInButtonView> {

    private static final String TAG = "SignInButtonPresenter";

    public void signIn(UserSignInModel userSignInModel) {
        if (userSignInModel != null) {
            getViewState().showProgressDialog();
            ApiService.authorizate(
                    userSignInModel.getEmail(),
                    userSignInModel.getPass(),
                    new OnUserAuthorizateListener() {
                        @Override
                        public void onSuccess(Cookie cookie, String userId) {
                            initUserCookie(cookie, userId);
                            cacheUser(userSignInModel, cookie);
                            getViewState().hideProgressDialog();
                            getViewState().startProfileActivity();
                        }

                        @Override
                        public void onError(Throwable t, String errorMessage) {
                            getViewState().hideProgressDialog();
                            getViewState().showSignInButtonToast("Ошибка авторизации");
                            Log.i(TAG, t.toString());
                        }
                    });
        }
    }

    private void initUserCookie(Cookie cookie, String ctUserId) {
        UserCookie.setCbtl(cookie.getCbtl());
        UserCookie.setCbtp(cookie.getCbtp());
        UserCookie.setLang(cookie.getLang());
        UserCookie.setNight(cookie.getNight());
        UserCookie.setNoprev(cookie.getNoprev());
        UserCookie.setPhpSessId(cookie.getPhpSessId());
        UserCookie.setCtUserId(ctUserId);
    }

    private void cacheUser(UserSignInModel userSignInModel, Cookie cookie) {
        DbService dbService = new DbService();
        UserCache userCache = new UserCache();
        userCache.setEmail(userSignInModel.getEmail());
        userCache.setPass(userSignInModel.getPass());
        userCache.setActive(true);
        userCache.setPhpSessId(cookie.getPhpSessId());
        dbService.updateUser(userCache);
    }

}
