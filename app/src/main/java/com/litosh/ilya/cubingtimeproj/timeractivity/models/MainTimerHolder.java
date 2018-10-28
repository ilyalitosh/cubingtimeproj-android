package com.litosh.ilya.cubingtimeproj.timeractivity.models;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.db.DbService;
import com.litosh.ilya.cubingtimeproj.db.DbSolveCrud;
import com.litosh.ilya.cubingtimeproj.db.models.DbConverter;
import com.litosh.ilya.cubingtimeproj.globalmodels.DateFormatter;
import com.litosh.ilya.cubingtimeproj.timeractivity.presenters.ScrambleViewPresenter;
import com.litosh.ilya.cubingtimeproj.timeractivity.ui.MyTimerActivity;
import com.litosh.ilya.cubingtimeproj.timeractivity.ui.Timer;
import com.litosh.ilya.cubingtimeproj.timeractivity.ui.TimerFragment;
import com.litosh.ilya.cubingtimeproj.timeractivity.views.ScrambleViewView;

/**
 * MainTimerHolder
 *
 * @author Ilya Litosh
 */
public class MainTimerHolder extends RecyclerView.ViewHolder implements ScrambleViewView {

    private Context mContext;
    private OnScrambleGenerate mOnScrambleGenerate;

    public MainTimerHolder(View itemView, Context context) {
        super(itemView);
        mContext = context;
        initializePresenters();
        initializeComponents(itemView);
        initializeSmoothAnimationData();
        initializeListeners();
    }

    private Timer mTimer;
    private AppCompatTextView mScrambleView;
    private RelativeLayout mFooterButton;
    private TimerFragment mParentFragment;
    private RelativeLayout mCancelSpace;
    private AppCompatImageView mPointer;
    private void initializeComponents(View view) {
        mTimer = view.findViewById(R.id.activity_timer_viewpager_tab_timer_timer);
        mScrambleView = view.findViewById(R.id.activity_timer_viewpager_tab_timer_scramble);
        mFooterButton = view.findViewById(R.id.activity_timer_viewpager_tab_timer_footer_button);
        mCancelSpace = view.findViewById(R.id.activity_timer_viewpager_tab_timer_main_timer_cancel_space);
        mPointer = view.findViewById(R.id.activity_timer_viewpager_tab_timer_main_timer_pointer);
        mParentFragment = ((TimerFragment) ((MyTimerActivity) mContext).getTimerViewPagerAdapter()
                .getFragment(TimerData.TIMER_POSITION));
        mScrambleViewPresenter.updateScrambleView();
    }

    private OnFooterButtonClick mOnFooterButtonClick;
    private boolean isAdditionalDataShowing;
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
                solve.setDate(DateFormatter.getCurrentDateWithTime());
                solve.setDbId(dbSolveCrud.addSolve(DbConverter.toDbSolve(solve, true)));
                mScrambleViewPresenter.addSolveToSolvesList(solve, ((MyTimerActivity) mContext));
                mScrambleViewPresenter.newScramble();
                mScrambleViewPresenter.updateScrambleView();
            }
        });
        mCancelSpace.setOnClickListener(v -> {
            mParentFragment.getRecyclerView()
                    .smoothScrollBy(0, mSmoothEndValue, new DecelerateInterpolator(2));
            mCancelSpace.setVisibility(View.GONE);
            isAdditionalDataShowing = false;
        });
        mFooterButton.setOnClickListener(v -> {
            mOnFooterButtonClick.onClick();
            if (isAdditionalDataShowing) {
                mParentFragment.getRecyclerView()
                        .smoothScrollBy(0, mSmoothEndValue, new DecelerateInterpolator(2));
                mCancelSpace.setVisibility(View.GONE);
                isAdditionalDataShowing = false;
            } else {
                mParentFragment.getRecyclerView()
                        .smoothScrollBy(0, mSmoothStartValue, new DecelerateInterpolator(2));
                mCancelSpace.setVisibility(View.VISIBLE);
                isAdditionalDataShowing = true;
            }
            mOnScrambleGenerate.onGenerated(mScrambleViewPresenter.getCurrentScrambleGraph());
        });
    }

    public AppCompatImageView getPointer() {
        return mPointer;
    }

    public void setOnScrambleGenerate(OnScrambleGenerate mOnScrambleGenerate) {
        this.mOnScrambleGenerate = mOnScrambleGenerate;
    }

    public void setOnFooterButtonClick(OnFooterButtonClick mOnFooterButtonClick) {
        this.mOnFooterButtonClick = mOnFooterButtonClick;
    }

    private int mSmoothStartValue;
    private int mSmoothEndValue;
    private void initializeSmoothAnimationData() {
        mSmoothStartValue = mParentFragment.getTabTimerListAdapter().getAdditionalDataViewHeight();
        mSmoothEndValue = 0 - mSmoothStartValue;
    }

    private ScrambleViewPresenter mScrambleViewPresenter;
    private void initializePresenters() {
        mScrambleViewPresenter = new ScrambleViewPresenter(this);
    }

    @Override
    public void updateScrambleViewText(Scramble scramble) {
        mScrambleView.setText(scramble.getScramble());
    }

    /**
     * OnFooterButtonClick
     * Слушатель слика по FooterButton
     *
     * @author Ilya Litosh
     */
    public interface OnFooterButtonClick {

        /**
         * Вызывается при клике по FooterButton
         *
         */
        void onClick();

    }

}
