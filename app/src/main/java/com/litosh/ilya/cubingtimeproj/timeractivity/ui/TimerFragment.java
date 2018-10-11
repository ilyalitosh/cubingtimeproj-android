package com.litosh.ilya.cubingtimeproj.timeractivity.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.Solve;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.Time;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.TimerData;

/**
 * TimerFragment
 *
 * @author Ilya Litosh
 */
public class TimerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_timer_viewpager_tab_timer, container, false);

        initializeComponents(view);
        initializeListeners();

        return view;
    }

    private Timer mTimer;
    private void initializeComponents(View view) {
        mTimer = view.findViewById(R.id.activity_timer_viewpager_tab_timer_timer);
    }

    private void initializeListeners() {
        mTimer.setOnTouchTimerListener(new Timer.OnTouchTimerListener() {
            @Override
            public void onTouch() {

            }

            @Override
            public void onDetach(Time time) {
                Solve solve = new Solve();
                solve.setTime(time);
                solve.setScramble("R' D2 L' U2 L U2 R2 B2 L' F2 L F' L B' R D F' L F D2 F2");
                try {
                    ((SolvesFragment) getParentActivity().getTimerViewPagerAdapter().getFragment(TimerData.SOLVES_POSITION))
                            .getSolvesListAdapter()
                            .addItem(solve);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private MyTimerActivity getParentActivity() {
        return ((MyTimerActivity) getActivity());
    }

}
