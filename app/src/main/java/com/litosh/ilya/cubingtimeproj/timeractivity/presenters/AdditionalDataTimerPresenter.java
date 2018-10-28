package com.litosh.ilya.cubingtimeproj.timeractivity.presenters;

import com.litosh.ilya.cubingtimeproj.db.DbService;
import com.litosh.ilya.cubingtimeproj.db.DbSolveCrud;
import com.litosh.ilya.cubingtimeproj.db.models.solves.Solve;
import com.litosh.ilya.cubingtimeproj.db.models.solves.Time;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.AdditionalDataModel;
import com.litosh.ilya.cubingtimeproj.timeractivity.views.AdditionalDataTimerView;

import java.util.List;

/**
 * AdditionalDataTimerPresenter
 *
 * @author Ilya Litosh
 */
public class AdditionalDataTimerPresenter {

    private AdditionalDataTimerView mAdditionalDataTimerView;

    public AdditionalDataTimerPresenter(AdditionalDataTimerView additionalDataTimerView) {
        mAdditionalDataTimerView = additionalDataTimerView;
    }

    /**
     * Обработка статистики на AdditionalDataTimer вью
     *
     */
    public void processStats() {
        AdditionalDataModel additionalDataModel = new AdditionalDataModel();
        DbSolveCrud dbSolveCrud = new DbService();
        additionalDataModel.setAllSolves(getAllSolves(dbSolveCrud));
        additionalDataModel.setSessionAvg(getSessionAvg(dbSolveCrud));
        additionalDataModel.setBestSolveSession(getBestSolveSession(dbSolveCrud));
        additionalDataModel.setWorstSolveSession(getWorstSolveSession(dbSolveCrud));

        mAdditionalDataTimerView.updateAdditionalData(additionalDataModel);
    }

    private String getAllSolves(DbSolveCrud dbSolveCrud) {
        return String.valueOf(dbSolveCrud.getSolves().size());
    }

    private String getSessionAvg(DbSolveCrud dbSolveCrud) {
        List<Solve> solves = dbSolveCrud.getMiddleSolves();
        if (solves == null) {
            return "0.000";
        }
        long summaryTime = 0;
        for (Solve solve: solves) {
            summaryTime += getTimeMillis(solve.getTime().getTarget());
        }

        return String.valueOf(summaryTime/solves.size()/1000.0);
    }

    private long getTimeMillis(Time time) {
        long hours = time.getHours();
        long minutes = time.getMinutes();
        long seconds = time.getSeconds();
        long milliseconds = time.getMilliseconds();

        return (hours * 3600 * 1000) + (minutes * 60 * 100) + (seconds * 1000) + milliseconds;
    }

    private String getBestSolveSession(DbSolveCrud dbSolveCrud) {
        Solve bestSolve = dbSolveCrud.getBestSolve();
        if (bestSolve != null) {
            return String.valueOf(getTimeMillis(bestSolve.getTime().getTarget())/1000.0);
        } else {
            return "0.000";
        }
    }

    private String getWorstSolveSession(DbSolveCrud dbSolveCrud) {
        Solve worstSolve = dbSolveCrud.getWorstSolve();
        if (worstSolve != null) {
            return String.valueOf(getTimeMillis(worstSolve.getTime().getTarget())/1000.0);
        } else {
            return "0.000";
        }
    }

}
