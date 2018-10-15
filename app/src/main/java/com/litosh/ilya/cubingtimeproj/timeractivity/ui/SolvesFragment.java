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
import com.litosh.ilya.cubingtimeproj.timeractivity.models.Solve;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.adapters.SolvesListAdapter;
import com.litosh.ilya.cubingtimeproj.timeractivity.presenters.SolvesListPresenter;
import com.litosh.ilya.cubingtimeproj.timeractivity.views.SolvesListView;

import java.util.ArrayList;
import java.util.List;

/**
 * SolvesFragment
 *
 * @author Ilya Litosh
 */
public class SolvesFragment extends Fragment implements SolvesListView {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_timer_viewpager_tab_solves, container, false);

        initializePresenters();
        initializeComponents(view);
        initializeListeners();

        return view;
    }

    private RecyclerView mScoresList;
    private void initializeComponents(View view) {
        mScoresList = view.findViewById(R.id.activity_timer_viewpager_tab_solves_solves_list);
        mSolvesListPresenter.initializeList();
    }

    private SolvesListPresenter mSolvesListPresenter;
    private void initializePresenters() {
        mSolvesListPresenter = new SolvesListPresenter(this);
    }

    public SolvesListAdapter getSolvesListAdapter() {
        return mSolvesListAdapter;
    }

    private void initializeListeners() {

    }

    private SolvesListAdapter mSolvesListAdapter;
    @Override
    public void updateSolvesList(List<Solve> solves) {
        mSolvesListAdapter = new SolvesListAdapter(getActivity(), solves);
        mScoresList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mScoresList.setAdapter(mSolvesListAdapter);
    }
}
