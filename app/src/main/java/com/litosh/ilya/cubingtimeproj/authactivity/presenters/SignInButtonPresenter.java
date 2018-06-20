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
import com.litosh.ilya.cubingtimeproj.db.models.User;
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
        getViewState().showProgressDialog();
        if (userSignInModel != null) {
            ApiService.authorizate(
                    userSignInModel.getEmail(),
                    userSignInModel.getPass(),
                    new OnUserAuthorizateListener() {
                        @Override
                        public void onSuccess(Cookie cookie, String userId) {
                            initUserCookie(cookie);
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

    private void initUserCookie(Cookie cookie) {
        UserCookie.setCbtl(cookie.getCbtl());
        UserCookie.setCbtp(cookie.getCbtp());
        UserCookie.setLang(cookie.getLang());
        UserCookie.setNight(cookie.getNight());
        UserCookie.setNoprev(cookie.getNoprev());
        UserCookie.setPhpSessId(cookie.getPhpSessId());
    }

    private void cacheUser(UserSignInModel userSignInModel, Cookie cookie) {
        DbService dbService = new DbService();
        User user = new User();
        user.setId(1);
        user.setEmail(userSignInModel.getEmail());
        user.setPass(userSignInModel.getPass());
        user.setActive(true);
        user.setPhpSessId(cookie.getPhpSessId());
        if (dbService.getUser() == null) {
            dbService.addUser(user);
        } else {
            dbService.updateUser(user);
        }
    }

}
