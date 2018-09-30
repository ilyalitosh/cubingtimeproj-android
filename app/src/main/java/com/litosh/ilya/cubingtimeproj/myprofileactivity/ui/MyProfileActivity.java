package com.litosh.ilya.cubingtimeproj.myprofileactivity.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.litosh.ilya.ct_sdk.models.profile.Note;
import com.litosh.ilya.ct_sdk.models.profile.User;
import com.litosh.ilya.ct_sdk.models.profile.Wall;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.baseactivity.ui.BaseActivity;
import com.litosh.ilya.cubingtimeproj.myprofileactivity.models.RestoredMyProfileData;
import com.litosh.ilya.cubingtimeproj.myprofileactivity.models.adapters.ProfileListAdapter;
import com.litosh.ilya.cubingtimeproj.myprofileactivity.presenters.UserProfilePresenter;
import com.litosh.ilya.cubingtimeproj.myprofileactivity.views.UserProfileView;

/**
 * MyProfileActivity
 *
 * @author Ilya Litosh
 */
public class MyProfileActivity extends BaseActivity implements UserProfileView {

    private UserProfilePresenter mUserProfilePresenter;
    private RestoredMyProfileData mRestoredMyProfileData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        isInitProfileAdapterAvailable = true;
        if (savedInstanceState != null) {
            mRestoredMyProfileData = new RestoredMyProfileData();
            mRestoredMyProfileData.getRestoredWall().push(
                    (Wall) savedInstanceState.getSerializable("my-profile-fragment-list-adapter-wall"));
            mRestoredMyProfileData.getRestoredUser().push(
                    (User) savedInstanceState.getSerializable("my-profile-fragment-list-adapter-user"));
        }
    }

    private boolean isInitProfileAdapterAvailable;
    @Override
    protected void onResume() {
        super.onResume();
        if (isInitProfileAdapterAvailable) {
            if (mRestoredMyProfileData == null) {
                initializeProfileAdapter();
            } else {
                mProfileListAdapter = new ProfileListAdapter(
                        this,
                        mRestoredMyProfileData.getRestoredWall().peek(),
                        mRestoredMyProfileData.getRestoredUser().peek());
                mProfileList.setLayoutManager(new LinearLayoutManager(this));
                mProfileList.setAdapter(mProfileListAdapter);
                mProfileList.getAdapter().notifyDataSetChanged();
                mProfileList.scheduleLayoutAnimation();
                //mRestoredMyProfileData.clearData();
            }
            isInitProfileAdapterAvailable = false;
        }
    }

    private void initializeProfileAdapter() {
        mSwipeRefreshLayout.setRefreshing(true);
        mUserProfilePresenter.initUserProfile();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_profile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_LIKES_CHANGES:
                if (resultCode == Activity.RESULT_OK) {
                    int positionInList = data.getExtras().getInt("note-position");
                    Note note = (Note) data.getExtras().getSerializable("note-data");
                    mProfileListAdapter.updateItem(note, positionInList);
                }
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("my-profile-fragment-list-adapter-wall", mProfileListAdapter.getWall());
        outState.putSerializable("my-profile-fragment-list-adapter-user", mProfileListAdapter.getUser());
    }

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mProfileList;
    @Override
    public void initActivityComponents() {
        super.initActivityComponents();
        mProfileList = findViewById(R.id.fragment_my_profile_profile_list);
        mSwipeRefreshLayout = findViewById(R.id.activity_profile_swipe_refresh_layout);
        mUserProfilePresenter = new UserProfilePresenter(this);
        setCustomSwipeRefreshLayoutStyle();
    }

    private void setCustomSwipeRefreshLayoutStyle() {
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorMain,
                R.color.colorMain,
                R.color.colorMain);
    }

    @Override
    public void initActivityListeners() {
        super.initActivityListeners();
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mUserProfilePresenter.initUserProfile();
        });
    }

    private ProfileListAdapter mProfileListAdapter;
    @Override
    public void initializeUserProfile(Wall wall, User user) {
        mProfileListAdapter = new ProfileListAdapter(this, wall, user);
        mProfileList.setLayoutManager(new LinearLayoutManager(this));
        mProfileList.setAdapter(mProfileListAdapter);
        mProfileList.getAdapter().notifyDataSetChanged();
        mProfileList.scheduleLayoutAnimation();
    }

    @Override
    public void hideProgressBar() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
