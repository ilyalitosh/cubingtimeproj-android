package com.litosh.ilya.cubingtimeproj.timeractivity.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilya.litosh.ScrambleGenerator;
import com.ilya.litosh.Size;
import com.ilya.litosh.Type;
import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.db.DbService;
import com.litosh.ilya.cubingtimeproj.db.DbSolveCrud;
import com.litosh.ilya.cubingtimeproj.db.models.DbConverter;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.Scramble;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.Solve;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.Time;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.TimerData;
import com.litosh.ilya.cubingtimeproj.timeractivity.presenters.ScrambleViewPresenter;
import com.litosh.ilya.cubingtimeproj.timeractivity.views.ScrambleViewView;

/**
 * TimerFragment
 *
 * @author Ilya Litosh
 */
public class TimerFragment extends Fragment implements ScrambleViewView {

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

    private Timer mTimer;
    private AppCompatTextView mScrambleView;
    private void initializeComponents(View view) {
        mTimer = view.findViewById(R.id.activity_timer_viewpager_tab_timer_timer);
        mScrambleView = view.findViewById(R.id.activity_timer_viewpager_tab_timer_scramble);
        mScrambleViewPresenter.updateScrambleView();
    }

    private ScrambleViewPresenter mScrambleViewPresenter;
    private void initializePresenters() {
        mScrambleViewPresenter = new ScrambleViewPresenter(this);
    }

    private void initializeListeners() {
        mTimer.setOnTouchTimerListener(new Timer.OnTouchTimerListener() {
            @Override
            public void onTouch() {

            }

            @Override
            public void onDetach(Time time) {
                DbSolveCrud dbSolveCrud = new DbService();
                Solve solve = new Solve();
                solve.setTime(time);
                solve.setScramble(mScrambleViewPresenter.getCurrentScramble());
                dbSolveCrud.addSolve(DbConverter.toDbSolve(solve));
                mScrambleViewPresenter.addSolveToSolvesList(solve, getParentActivity());
                mScrambleViewPresenter.newScramble();
                mScrambleViewPresenter.updateScrambleView();
            }
        });
    }

    private MyTimerActivity getParentActivity() {
        return ((MyTimerActivity) getActivity());
    }

    @Override
    public void updateScrambleViewText(Scramble scramble) {
        mScrambleView.setText(scramble.getScramble());
    }
}
