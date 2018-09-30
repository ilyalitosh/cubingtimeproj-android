package com.litosh.ilya.cubingtimeproj.myprofileactivity.models;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.litosh.ilya.cubingtimeproj.R;

public class UserInfoHolder extends RecyclerView.ViewHolder {

    private AppCompatTextView mCountry;
    private AppCompatTextView mCity;
    private AppCompatTextView mSex;
    private AppCompatTextView mWca;

    public UserInfoHolder(View itemView) {
        super(itemView);
        mCountry = itemView.findViewById(R.id.activity_profile_user_info_country_title);
        mCity = itemView.findViewById(R.id.activity_profile_user_info_city_title);
        mSex = itemView.findViewById(R.id.activity_profile_user_info_sex_title);
        mWca = itemView.findViewById(R.id.activity_profile_user_info_wca_title);
    }

    public AppCompatTextView getCountry() {
        return mCountry;
    }

    public AppCompatTextView getCity() {
        return mCity;
    }

    public AppCompatTextView getSex() {
        return mSex;
    }

    public AppCompatTextView getWca() {
        return mWca;
    }
}
