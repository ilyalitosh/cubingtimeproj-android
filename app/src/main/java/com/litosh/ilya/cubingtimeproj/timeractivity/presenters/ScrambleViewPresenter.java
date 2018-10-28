package com.litosh.ilya.cubingtimeproj.timeractivity.presenters;

import com.ilya.litosh.RubiksCubeGraph;
import com.ilya.litosh.ScrambleGenerator;
import com.ilya.litosh.ScrambleGraph;
import com.ilya.litosh.Size;
import com.ilya.litosh.Type;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.Scramble;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.Solve;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.TimerData;
import com.litosh.ilya.cubingtimeproj.timeractivity.ui.MyTimerActivity;
import com.litosh.ilya.cubingtimeproj.timeractivity.ui.SolvesFragment;
import com.litosh.ilya.cubingtimeproj.timeractivity.views.ScrambleViewView;

import java.util.List;

/**
 * ScrambleViewPresenter
 *
 * @author Ilya Litosh
 */
public class ScrambleViewPresenter {

    private ScrambleViewView mScrambleViewView;
    private ScrambleGenerator mScrambleGenerator;
    private Scramble mScramble;

    public ScrambleViewPresenter(ScrambleViewView scrambleViewView) {
        mScrambleViewView = scrambleViewView;
        mScrambleGenerator = new ScrambleGenerator(Type.RUBIKS_CUBE, Size.GEN_3_X_3);
        mScramble = newScramble();
    }

    /**
     * Генерация нового скрамбла
     *
     */
    public Scramble newScramble() {
        mScramble = Scramble.createScramble(toScrambleString(mScrambleGenerator.generate(20)));
        return mScramble;
    }

    public Scramble getCurrentScramble() {
        return mScramble;
    }

    /**
     * Обновление ScrambleView
     *
     */
    public void updateScrambleView() {
        mScrambleViewView.updateScrambleViewText(getCurrentScramble());
    }

    /**
     * Добавление сборки в список сборок
     *
     * @param solve сборка
     * @param myTimerActivity родительская активити
     */
    public void addSolveToSolvesList(Solve solve, MyTimerActivity myTimerActivity) {
        try {
            ((SolvesFragment) myTimerActivity.getTimerViewPagerAdapter().getFragment(TimerData.SOLVES_POSITION))
                    .getSolvesListAdapter()
                    .addItem(solve);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RubiksCubeGraph getCurrentScrambleGraph() {
        return mScrambleGenerator.getGraph();
    }

    private String toScrambleString(List<String> scramble) {
        StringBuilder scrambleString = new StringBuilder();
        for (String s: scramble) {
            scrambleString.append(s).append(" ");
        }

        return scrambleString.toString();
    }

}
