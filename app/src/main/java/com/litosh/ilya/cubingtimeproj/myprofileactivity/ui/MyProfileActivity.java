package com.litosh.ilya.cubingtimeproj.myprofileactivity.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.baseactivity.ui.BaseActivity;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.ui.MyProfileFragment;

/**
 * MyProfileActivity
 *
 * Created by ilya_ on 19.06.2018.
 */

public class MyProfileActivity extends BaseActivity {

    private static final String TAG = "MyProfileActivity";
    private MyProfileFragment mMyProfileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initMyProfileFragment();

    }

    private void initMyProfileFragment() {
        mMyProfileFragment = new MyProfileFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.activity_base_fragment_container, mMyProfileFragment);
        ft.commit();
    }
}
