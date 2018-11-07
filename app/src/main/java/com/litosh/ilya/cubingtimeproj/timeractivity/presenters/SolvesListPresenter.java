package com.litosh.ilya.cubingtimeproj.timeractivity.presenters;

import com.litosh.ilya.cubingtimeproj.db.DbService;
import com.litosh.ilya.cubingtimeproj.db.DbSolveCrud;
import com.litosh.ilya.cubingtimeproj.db.models.DbConverter;
import com.litosh.ilya.cubingtimeproj.db.models.solves.Solve;
import com.litosh.ilya.cubingtimeproj.timeractivity.views.SolvesListView;

import java.util.ArrayList;
import java.util.List;

/**
 * SolvesListPresenter
 *
 * @author Ilya Litosh
 */
public class SolvesListPresenter {

    private SolvesListView mSolvesListView;

    public SolvesListPresenter(SolvesListView solvesListView) {
        mSolvesListView = solvesListView;
    }

    public void initializeList() {
        DbSolveCrud dbSolveCrud = new DbService();
        List<Solve> dbSolves = dbSolveCrud.getSolves();
        if (dbSolves.size() > 0) {
            mSolvesListView.updateSolvesList(toSolves(dbSolves));
        } else {
            mSolvesListView.updateSolvesList(new ArrayList<>());
        }
    }

    public void deleteSolve(com.litosh.ilya.cubingtimeproj.timeractivity.models.Solve solve,
                            int position) {
        DbSolveCrud dbSolveCrud = new DbService();
        dbSolveCrud.removeSolve(DbConverter.toDbSolve(solve, false));
        mSolvesListView.updateSolveListAfterDeletedSolve(position);
    }

    private List<com.litosh.ilya.cubingtimeproj.timeractivity.models.Solve> toSolves(
            List<Solve> dbSolves) {
        List<com.litosh.ilya.cubingtimeproj.timeractivity.models.Solve> solves = new ArrayList<>();
        for (Solve solve: dbSolves) {
            solves.add(DbConverter.toSolve(solve));
        }

        // FIXME For DEBUG
//        for (com.litosh.ilya.cubingtimeproj.timeractivity.models.Solve solve: solves) {
//            System.out.println(solve.getTime().toString() + " " + solve.getScramble().getScramble() + " " + solve.getSolveType());
//        }

        return solves;
    }

}
