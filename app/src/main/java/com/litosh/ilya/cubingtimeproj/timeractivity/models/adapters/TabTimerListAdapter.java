package com.litosh.ilya.cubingtimeproj.timeractivity.models.adapters;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ilya.litosh.RubiksCubeGraph;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.AdditionalDataTimerHolder;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.MainTimerHolder;

import java.util.List;

/**
 * TabTimerListAdapter
 *
 * @author Ilya Litosh
 */
public class TabTimerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private MainTimerHolder mMainTimerHolder;
    private AdditionalDataTimerHolder mAdditionalDataTimerHolder;
    private RubiksCubeGraph mBufferRubiksCubeGraph;

    public TabTimerListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewMainTimer = mLayoutInflater.inflate(
                R.layout.activity_timer_viewpager_tab_timer_main_timer,
                parent,
                false);
        View viewAdditionalData = mLayoutInflater.inflate(
                R.layout.activity_timer_viewpager_tab_timer_additional_data,
                parent,
                false);
        mMainTimerViewHeight = getViewHeight(viewMainTimer);
        mAdditionalDataViewHeight = getViewHeight(viewAdditionalData);

        switch (viewType) {
            case MAIN_TIMER:
                mMainTimerHolder = new MainTimerHolder(viewMainTimer, mLayoutInflater.getContext());
                mMainTimerHolder.setOnScrambleGenerate(rubiksCubeGraph -> {
                    mBufferRubiksCubeGraph = rubiksCubeGraph;
                    if (mAdditionalDataTimerHolder != null) {
                        mBufferRubiksCubeGraph.ScrambleGUI();
                        mAdditionalDataTimerHolder.getScrambleGraph().setRubiksCubeGraph(mBufferRubiksCubeGraph);
                    }
                });
                mMainTimerHolder.setOnFooterButtonClick(() -> {
                    if (mAdditionalDataTimerHolder != null) {
                        mAdditionalDataTimerHolder.updateStats();
                    }
                });
                return mMainTimerHolder;
            case ADDITIONAL_DATA:
                mAdditionalDataTimerHolder = new AdditionalDataTimerHolder(viewAdditionalData, mLayoutInflater.getContext());
                mBufferRubiksCubeGraph.ScrambleGUI();
                mAdditionalDataTimerHolder.getScrambleGraph().setRubiksCubeGraph(mBufferRubiksCubeGraph);
                mAdditionalDataTimerHolder.updateStats();
                return mAdditionalDataTimerHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    private int getViewHeight(View view) {
        WindowManager wm = (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int deviceWidth = display.getWidth();

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.EXACTLY);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);

        return view.getMeasuredHeight();
    }

    @Override
    public int getItemCount() {
        return LIST_ELEMENTS_COUNT;
    }

    public int getMainTimerViewHeight() {
        return mMainTimerViewHeight;
    }

    public int getAdditionalDataViewHeight() {
        return mAdditionalDataViewHeight;
    }

    public MainTimerHolder getMainTimerHolder() {
        return mMainTimerHolder;
    }

    public AdditionalDataTimerHolder getAdditionalDataTimerHolder() {
        return mAdditionalDataTimerHolder;
    }

    private int mMainTimerViewHeight;
    private int mAdditionalDataViewHeight;

    private static final int LIST_ELEMENTS_COUNT = 2;

    private static final int MAIN_TIMER = 0;
    private static final int ADDITIONAL_DATA = 1;

}
