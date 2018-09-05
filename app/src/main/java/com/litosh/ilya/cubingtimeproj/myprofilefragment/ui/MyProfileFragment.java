package com.litosh.ilya.cubingtimeproj.myprofilefragment.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.litosh.ilya.ct_sdk.models.profile.User;
import com.litosh.ilya.ct_sdk.models.profile.Wall;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.models.adapters.ProfileListAdapter;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.presenters.UserProfilePresenter;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.views.UserProfileView;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * MyProfileFragment
 *
 * @author Ilya Litosh
 */

public class MyProfileFragment extends MvpAppCompatFragment
        implements UserProfileView {

    @InjectPresenter
    UserProfilePresenter mUserProfilePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile, null);

        initComponents(view);
        initListeners();

        return view;
    }

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mProfileList;
    private void initComponents(View view) {
        mProfileList = view.findViewById(R.id.fragment_my_profile_profile_list);
        mSwipeRefreshLayout = view.findViewById(R.id.activity_profile_swipe_refresh_layout);
        setCustomSwipeRefreshLayoutStyle();
        //setOverScrolling();

        mSwipeRefreshLayout.setRefreshing(true);
        mUserProfilePresenter.initUserProfile();
    }

    private void initListeners() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mUserProfilePresenter.initUserProfile();
        });
    }

    private void setOverScrolling() {
        OverScrollDecoratorHelper.setUpStaticOverScroll(
                mProfileList, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
    }

    private void setCustomSwipeRefreshLayoutStyle() {
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorMain,
                R.color.colorMain,
                R.color.colorMain);
    }

    private ProfileListAdapter mProfileListAdapter;
    @Override
    public void initializeUserProfile(Wall wall, User user) {
        mProfileListAdapter = new ProfileListAdapter(getActivity(), wall, user);
        mProfileList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProfileList.setAdapter(mProfileListAdapter);
        mProfileList.getAdapter().notifyDataSetChanged();
        mProfileList.scheduleLayoutAnimation();
    }

    @Override
    public void hideProgressBar() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
