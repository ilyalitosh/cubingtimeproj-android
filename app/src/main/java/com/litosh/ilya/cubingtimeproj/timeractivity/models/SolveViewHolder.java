package com.litosh.ilya.cubingtimeproj.timeractivity.models;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.litosh.ilya.cubingtimeproj.R;

public class SolveViewHolder extends RecyclerView.ViewHolder {

    private AppCompatTextView mTime;
    private AppCompatTextView mScramble;

    public SolveViewHolder(View itemView) {
        super(itemView);
        mTime = itemView.findViewById(
                R.id.activity_timer_viewpager_tab_solves_solves_list_solve_item_time);
        mScramble = itemView.findViewById(
                R.id.activity_timer_viewpager_tab_solves_solves_list_solve_item_scramble);
    }

    public AppCompatTextView getTime() {
        return mTime;
    }

    public AppCompatTextView getScramble() {
        return mScramble;
    }
}
