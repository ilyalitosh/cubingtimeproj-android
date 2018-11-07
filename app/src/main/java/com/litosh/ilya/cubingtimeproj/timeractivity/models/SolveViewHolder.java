package com.litosh.ilya.cubingtimeproj.timeractivity.models;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.timeractivity.ui.MoreFeaturesSolveItemMenu;

/**
 * SolveViewHolder
 *
 * @author Ilya Litosh
 */
public class SolveViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private MoreFeaturesSolveItemMenu.OnFeatureClick mOnFeatureClick;
    private AppCompatTextView mTime;
    private AppCompatTextView mScramble;
    private AppCompatImageView mMoreFeaturesButton;
    private AppCompatTextView mDateWithTime;
    private Solve mSolve;

    public SolveViewHolder(View itemView, Context context) {
        super(itemView);
        mContext = context;
        mTime = itemView.findViewById(
                R.id.activity_timer_viewpager_tab_solves_solves_list_solve_item_time);
        mScramble = itemView.findViewById(
                R.id.activity_timer_viewpager_tab_solves_solves_list_solve_item_scramble);
        mMoreFeaturesButton = itemView.findViewById(
                R.id.activity_timer_viewpager_tab_solves_solves_list_solve_item_more_features_button);
        mDateWithTime = itemView.findViewById(
                R.id.activity_timer_viewpager_tab_solves_solves_list_solve_item_date);
        initializeListeners();
    }

    public void setSolve(Solve mSolve) {
        this.mSolve = mSolve;
    }

    public AppCompatTextView getTime() {
        return mTime;
    }

    public AppCompatTextView getScramble() {
        return mScramble;
    }

    public AppCompatImageView getMoreFeaturesButton() {
        return mMoreFeaturesButton;
    }

    public AppCompatTextView getDateWithTime() {
        return mDateWithTime;
    }

    public void setOnFeatureClick(MoreFeaturesSolveItemMenu.OnFeatureClick mOnFeatureClick) {
        this.mOnFeatureClick = mOnFeatureClick;
    }

    private void initializeListeners() {
        mMoreFeaturesButton.setOnClickListener(v -> {
            MoreFeaturesSolveItemMenu moreFeaturesSolveItemMenu =
                    new MoreFeaturesSolveItemMenu(mContext, v, mSolve, getAdapterPosition());
            moreFeaturesSolveItemMenu.setOnFeatureClick(mOnFeatureClick);
            moreFeaturesSolveItemMenu.show();
        });
    }
}
