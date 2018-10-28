package com.litosh.ilya.cubingtimeproj.timeractivity.models;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.timeractivity.presenters.AdditionalDataTimerPresenter;
import com.litosh.ilya.cubingtimeproj.timeractivity.ui.ScrambleGraph;
import com.litosh.ilya.cubingtimeproj.timeractivity.views.AdditionalDataTimerView;

/**
 * AdditionalDataTimerHolder
 *
 * @author Ilya Litosh
 */
public class AdditionalDataTimerHolder extends RecyclerView.ViewHolder implements AdditionalDataTimerView {

    private ScrambleGraph mScrambleGraph;
    private AppCompatTextView mAllSolves;
    private AppCompatTextView mSessionAvg;
    private AppCompatTextView mBestSolveSession;
    private AppCompatTextView mWorstSolveSession;
    private AppCompatTextView mCurrentAvg3;
    private AppCompatTextView mCurrentAvg5;
    private AppCompatTextView mCurrentAvg12;
    private AppCompatTextView mCurrentAvg100;

    public AdditionalDataTimerHolder(View itemView, Context context) {
        super(itemView);
        mScrambleGraph = itemView.findViewById(
                R.id.activity_timer_viewpager_tab_timer_additional_data_scramble_graph);
        mAllSolves = itemView.findViewById(
                R.id.activity_timer_viewpager_tab_timer_additional_data_all_solves_count);
        mSessionAvg = itemView.findViewById(
                R.id.activity_timer_viewpager_tab_timer_additional_data_session_average);
        mBestSolveSession = itemView.findViewById(
                R.id.activity_timer_viewpager_tab_timer_additional_data_best_solve_session);
        mWorstSolveSession = itemView.findViewById(
                R.id.activity_timer_viewpager_tab_timer_additional_data_worst_solve_session);
        mCurrentAvg3 = itemView.findViewById(
                R.id.activity_timer_viewpager_tab_timer_additional_data_current_avg_3);
        mCurrentAvg5 = itemView.findViewById(
                R.id.activity_timer_viewpager_tab_timer_additional_data_current_avg_5);
        mCurrentAvg12 = itemView.findViewById(
                R.id.activity_timer_viewpager_tab_timer_additional_data_current_avg_12);
        mCurrentAvg100 = itemView.findViewById(
                R.id.activity_timer_viewpager_tab_timer_additional_data_current_avg_100);
        initializePresenters();
    }

    /**
     * Обновляет вью со статистикой
     *
     */
    public void updateStats() {
        mAdditionalDataTimerPresenter.processStats();
    }

    private AdditionalDataTimerPresenter mAdditionalDataTimerPresenter;
    private void initializePresenters() {
        mAdditionalDataTimerPresenter = new AdditionalDataTimerPresenter(this);
    }

    public ScrambleGraph getScrambleGraph() {
        return mScrambleGraph;
    }

    public AppCompatTextView getAllSolves() {
        return mAllSolves;
    }

    public AppCompatTextView getSessionAvg() {
        return mSessionAvg;
    }

    public AppCompatTextView getBestSolveSession() {
        return mBestSolveSession;
    }

    public AppCompatTextView getWorstSolveSession() {
        return mWorstSolveSession;
    }

    public AppCompatTextView getCurrentAvg3() {
        return mCurrentAvg3;
    }

    public AppCompatTextView getCurrentAvg5() {
        return mCurrentAvg5;
    }

    public AppCompatTextView getCurrentAvg12() {
        return mCurrentAvg12;
    }

    public AppCompatTextView getCurrentAvg100() {
        return mCurrentAvg100;
    }

    @Override
    public void updateAdditionalData(AdditionalDataModel additionalDataModel) {
        mAllSolves.setText(additionalDataModel.getAllSolves());
        mSessionAvg.setText(additionalDataModel.getSessionAvg());
        mBestSolveSession.setText(additionalDataModel.getBestSolveSession());
        mWorstSolveSession.setText(additionalDataModel.getWorstSolveSession());
//        mCurrentAvg3.setText(additionalDataModel.getCurrentAvg3());
//        mCurrentAvg5.setText(additionalDataModel.getCurrentAvg5());
//        mCurrentAvg12.setText(additionalDataModel.getCurrentAvg12());
//        mCurrentAvg100.setText(additionalDataModel.getCurrentAvg100());
    }

}
