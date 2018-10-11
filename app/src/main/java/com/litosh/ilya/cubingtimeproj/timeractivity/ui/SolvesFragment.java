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
import com.litosh.ilya.cubingtimeproj.timeractivity.models.adapters.SolvesListAdapter;

import java.util.ArrayList;

public class SolvesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_timer_viewpager_tab_solves, container, false);

        initializeComponents(view);
        initializeListeners();

        return view;
    }

    private RecyclerView mScoresList;
    private SolvesListAdapter mSolvesListAdapter;
    private void initializeComponents(View view) {
        mScoresList = view.findViewById(R.id.activity_timer_viewpager_tab_solves_solves_list);
        mSolvesListAdapter = new SolvesListAdapter(getActivity(), new ArrayList<>());
        mScoresList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mScoresList.setAdapter(mSolvesListAdapter);
    }

    public SolvesListAdapter getSolvesListAdapter() {
        return mSolvesListAdapter;
    }

    public RecyclerView getScoresList() {
        return mScoresList;
    }

    private void initializeListeners() {

    }
}
