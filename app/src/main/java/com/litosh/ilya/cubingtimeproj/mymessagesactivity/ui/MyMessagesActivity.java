package com.litosh.ilya.cubingtimeproj.mymessagesactivity.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.litosh.ilya.cubingtimeproj.baseactivity.ui.BaseActivity;
import com.litosh.ilya.cubingtimeproj.mymessagesfragment.ui.MyMessagesFragment;

/**
 * MyMessagesActivity
 *
 * Created by ilya_ on 24.06.2018.
 */

public class MyMessagesActivity extends BaseActivity {

    private MyMessagesFragment mMyMessagesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initMyMessagesFragment();

    }

    private void initMyMessagesFragment() {
        mMyMessagesFragment = new MyMessagesFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(getFragmentContainer().getId(), mMyMessagesFragment);
        ft.commit();
    }

}
