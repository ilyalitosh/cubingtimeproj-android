package com.litosh.ilya.cubingtimeproj.myprofilefragment.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.litosh.ilya.ct_sdk.models.User;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.presenters.UserAvatarContainerPresenter;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.presenters.UserInfoTextViewsPresenter;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.views.UserAvatarContainerView;
import com.litosh.ilya.cubingtimeproj.myprofilefragment.views.UserInfoTextViewsView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.RequestCreator;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * MyProfileFragment
 *
 * Created by ilya_ on 24.06.2018.
 */

public class MyProfileFragment extends MvpAppCompatFragment
        implements UserInfoTextViewsView, UserAvatarContainerView {

    @InjectPresenter
    UserAvatarContainerPresenter mUserAvatarContainerPresenter;
    @InjectPresenter
    UserInfoTextViewsPresenter mUserInfoTextViewsPresenter;
    private AppCompatTextView mProfileName;
    private AppCompatTextView mActivity;
    private AppCompatTextView mCountry;
    private AppCompatTextView mCity;
    private AppCompatTextView mSex;
    private AppCompatTextView mWca;
    private AppCompatTextView mFriends;
    private RoundedImageView mAvatarContainer;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ScrollView mProfileScrollView;

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

    private void initComponents(View view) {
        mProfileName = view.findViewById(R.id.activity_profile_profilename_title);
        mActivity = view.findViewById(R.id.activity_profile_activity_title);
        mCountry = view.findViewById(R.id.activity_profile_user_info_country_title);
        mCity = view.findViewById(R.id.activity_profile_user_info_city_title);
        mSex = view.findViewById(R.id.activity_profile_user_info_sex_title);
        mWca = view.findViewById(R.id.activity_profile_user_info_wca_title);
        mFriends = view.findViewById(R.id.activity_profile_user_info_friends_value);
        mAvatarContainer = view.findViewById(R.id.activity_profile_avatar_container);
        mSwipeRefreshLayout = view.findViewById(R.id.activity_profile_swipe_refresh_layout);
        setCustomSwipeRefreshLayoutStyle();
        mProfileScrollView = view.findViewById(R.id.activity_profile_scroll_view_profile);
        setOverScrolling();

        mUserInfoTextViewsPresenter.initUserInfo();
    }

    private void initListeners() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mUserInfoTextViewsPresenter.initUserInfo();
        });
    }

    private void setOverScrolling() {
        OverScrollDecoratorHelper.setUpOverScroll(mProfileScrollView);
    }

    private void setCustomSwipeRefreshLayoutStyle() {
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorMain,
                R.color.colorMain,
                R.color.colorMain);
    }

    @Override
    public void initUserInfoTextViews(User user) {
        mProfileName.setHint(user.getProfileName());
        mActivity.setHint(user.getActivity());
        mCountry.setHint(user.getCountry());
        mCity.setHint(user.getCity());
        mSex.setHint(user.getSex());
        mWca.setHint(user.getWca());
        mFriends.setHint(user.getFriendsCount());
        mUserAvatarContainerPresenter.setAvatar(user);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setAvatar(RequestCreator requestCreator) {
        requestCreator.into(mAvatarContainer);
    }
}
