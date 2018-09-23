package com.litosh.ilya.cubingtimeproj.myprofileactivity.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.litosh.ilya.ct_sdk.models.profile.Note;
import com.litosh.ilya.cubingtimeproj.baseactivity.ui.BaseActivity;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.ui.MyProfileFragment;

/**
 * MyProfileActivity
 *
 * @author Ilya Litosh
 */
public class MyProfileActivity extends BaseActivity {

    public static final int REQUEST_CODE_LIKES_CHANGES = 1001;
    private MyProfileFragment mMyProfileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initMyProfileFragment();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_LIKES_CHANGES:
                if (resultCode == Activity.RESULT_OK) {
                    int positionInList = data.getExtras().getInt("note-position");
                    Note note = (Note) data.getExtras().getSerializable("note-data");
                    mMyProfileFragment.getProfileListAdapter().updateItem(note, positionInList);
                }
                break;
        }
    }

    private void initMyProfileFragment() {
        mMyProfileFragment = new MyProfileFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(getFragmentContainer().getId(), mMyProfileFragment);
        ft.commit();
    }
}
