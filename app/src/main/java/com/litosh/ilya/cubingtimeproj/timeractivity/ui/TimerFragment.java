package com.litosh.ilya.cubingtimeproj.timeractivity.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.adapters.TabTimerListAdapter;
import com.litosh.ilya.cubingtimeproj.timeractivity.presenters.PointerMainTimerPresenter;
import com.litosh.ilya.cubingtimeproj.timeractivity.views.PointerMainTimerView;

/**
 * TimerFragment
 *
 * @author Ilya Litosh
 */
public class TimerFragment extends Fragment implements PointerMainTimerView {

    private PointerMainTimerPresenter mPointerMainTimerPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_timer_viewpager_tab_timer, container, false);

        initializePresenters();
        initializeComponents(view);
        initializeListeners();

        return view;
    }

    private NonScrollingRecyclerView mRecyclerView;
    private TabTimerListAdapter mTabTimerListAdapter;
    private void initializeComponents(View view) {
        mRecyclerView = view.findViewById(R.id.activity_timer_viewpager_tab_timer_recycler_view);
        mTabTimerListAdapter = new TabTimerListAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mTabTimerListAdapter);
    }

    private void initializeListeners() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                mPointerMainTimerPresenter.rotate(
                        mTabTimerListAdapter.getMainTimerHolder().getPointer(),
                        dy,
                        mTabTimerListAdapter.getAdditionalDataViewHeight());
            }
        });
    }

    private void initializePresenters() {
        mPointerMainTimerPresenter = new PointerMainTimerPresenter(this);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public TabTimerListAdapter getTabTimerListAdapter() {
        return mTabTimerListAdapter;
    }

    private MyTimerActivity getParentActivity() {
        return ((MyTimerActivity) getActivity());
    }

    @Override
    public void rotatePointer(float value) {
        mTabTimerListAdapter.getMainTimerHolder().getPointer().setRotation(value);
    }
}
