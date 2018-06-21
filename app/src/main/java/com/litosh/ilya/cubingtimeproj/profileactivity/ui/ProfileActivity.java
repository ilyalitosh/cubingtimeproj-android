package com.litosh.ilya.cubingtimeproj.profileactivity.ui;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.litosh.ilya.ct_sdk.api.ApiService;
import com.litosh.ilya.ct_sdk.models.User;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.globalmodels.UserCookie;
import com.litosh.ilya.cubingtimeproj.profileactivity.presenters.UserInfoTextViewsPresenter;
import com.litosh.ilya.cubingtimeproj.profileactivity.views.UserInfoTextViewsView;

/**
 * ProfileActivity
 *
 * Created by ilya_ on 19.06.2018.
 */

public class ProfileActivity extends MvpAppCompatActivity implements UserInfoTextViewsView {

    @InjectPresenter
    UserInfoTextViewsPresenter mUserInfoTextViewsPresenter;
    private static final String TAG = "ProfileActivity";
    private AppCompatTextView mProfileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initComponents();

    }

    private void initComponents() {
        mProfileName = findViewById(R.id.activity_profile_profilename_title);

        mUserInfoTextViewsPresenter.initUserInfo();
    }


    @Override
    public void initUserInfoTextViews(User user) {
        mProfileName.setHint(user.getProfileName());
    }
}
