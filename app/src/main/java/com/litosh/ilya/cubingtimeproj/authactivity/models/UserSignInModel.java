package com.litosh.ilya.cubingtimeproj.authactivity.models;

/**
 * UserSignInModel модель с данными для авторизации
 *
 * Created by ilya_ on 19.06.2018.
 */

public class UserSignInModel {

    private String mEmail;
    private String mPass;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPass() {
        return mPass;
    }

    public void setPass(String mPass) {
        this.mPass = mPass;
    }
}
