package com.litosh.ilya.cubingtimeproj.db;

import com.litosh.ilya.cubingtimeproj.app.App;
import com.litosh.ilya.cubingtimeproj.db.models.SolvingAddingResponse;
import com.litosh.ilya.cubingtimeproj.db.models.UserCache;
import com.litosh.ilya.cubingtimeproj.db.models.solves.Solve;
import com.litosh.ilya.cubingtimeproj.db.models.solves.Solve_;
import com.litosh.ilya.cubingtimeproj.db.models.solves.Time;

import java.util.List;

import io.objectbox.Box;

/**
 * DbService репозиторий для доступа к локальной БД
 *
 * @author Ilya Litosh
 */

public class DbService implements DbUserCrud, DbSolveCrud {

    private static final String TAG = "OBoxDbService";

    @Override
    public void addUser(UserCache userCache) {
        App.getDbSession()
                .boxFor(UserCache.class)
                .put(userCache);
    }

    @Override
    public UserCache getUser() {
        return App.getDbSession()
                .boxFor(UserCache.class)
                .get(1);
    }

    @Override
    public void updateUser(UserCache userCache) {
        if (App.getDbSession().boxFor(UserCache.class).getAll().size() == 1) {
            userCache.setId(1);
        }
        App.getDbSession()
                .boxFor(UserCache.class)
                .put(userCache);
    }

    @Override
    public void deleteUser() {
        App.getDbSession()
                .boxFor(UserCache.class)
                .removeAll();
    }

    private static final int SOLVE_TYPE_WORST = -1;
    private static final int SOLVE_TYPE_BEST = 1;
    private static final int SOLVE_TYPE_NORMAL = 0;
    @Override
    public SolvingAddingResponse addSolve(Solve solve) {
        List<Solve> worstSolveList =
                App.getDbSession().boxFor(Solve.class).query().equal(Solve_.solveType, -1).build().find();
        List<Solve> bestSolveList =
                App.getDbSession().boxFor(Solve.class).query().equal(Solve_.solveType, 1).build().find();
        if (worstSolveList.size() == 0) {
            if (bestSolveList.size() == 0) {
                solve.setSolveType(SOLVE_TYPE_BEST);
            } else {
                long bestSolveTimeMillis = getTimeMillis(bestSolveList.get(0).getTime().getTarget());
                long currentSolveTimeMillis = getTimeMillis(solve.getTime().getTarget());
                if (bestSolveTimeMillis <= currentSolveTimeMillis) {
                    solve.setSolveType(SOLVE_TYPE_WORST);
                } else {
                    solve.setSolveType(SOLVE_TYPE_BEST);
                    Solve bestSolve = bestSolveList.get(0);
                    bestSolve.setSolveType(SOLVE_TYPE_WORST);
                    App.getDbSession().boxFor(Solve.class).put(bestSolve);
                }
            }
        } else {
            long bestSolveTimeMillis = getTimeMillis(bestSolveList.get(0).getTime().getTarget());
            long currentSolveTimeMillis = getTimeMillis(solve.getTime().getTarget());
            long worstSolveTimeMillis = getTimeMillis(worstSolveList.get(0).getTime().getTarget());
            if (currentSolveTimeMillis > worstSolveTimeMillis) {
                solve.setSolveType(SOLVE_TYPE_WORST);
                Solve worstSolve = worstSolveList.get(0);
                worstSolve.setSolveType(SOLVE_TYPE_NORMAL);
                App.getDbSession().boxFor(Solve.class).put(worstSolve);
            } else if (currentSolveTimeMillis <= worstSolveTimeMillis && currentSolveTimeMillis >= bestSolveTimeMillis) {
                solve.setSolveType(SOLVE_TYPE_NORMAL);
            } else if (currentSolveTimeMillis < bestSolveTimeMillis) {
                solve.setSolveType(SOLVE_TYPE_BEST);
                Solve bestSolve = bestSolveList.get(0);
                bestSolve.setSolveType(SOLVE_TYPE_NORMAL);
                App.getDbSession().boxFor(Solve.class).put(bestSolve);
            }
        }
        long solveId = App.getDbSession().boxFor(Solve.class).put(solve);
        long timeId = App.getDbSession().boxFor(Time.class).put(solve.getTime().getTarget());
        return new SolvingAddingResponse().builder()
                .solveId(solveId)
                .timeId(timeId)
                .build();
    }

    private long getTimeMillis(Time time) {
        long hours = time.getHours();
        long minutes = time.getMinutes();
        long seconds = time.getSeconds();
        long milliseconds = time.getMilliseconds();

        return (hours * 3600 * 1000) + (minutes * 60 * 100) + (seconds * 1000) + milliseconds;
    }

    @Override
    public List<Solve> getSolves() {
        return App.getDbSession().boxFor(Solve.class).getAll();
    }

    @Override
    public void removeSolve(Solve solve) {
        Box<Solve> transactionSolvesBox = App.getDbSession().boxFor(Solve.class);
        Box<Time> transactionTimeBox = App.getDbSession().boxFor(Time.class);
        int solveTypeOfRemovedSolve = solve.getSolveType();
        App.getDbSession().runInTx(() -> {
            transactionTimeBox.remove(solve.getTime().getTarget());
            transactionSolvesBox.remove(solve);
        });
        if (solveTypeOfRemovedSolve == SOLVE_TYPE_WORST) {
            updateSolve(getNewWorstSolve(App.getDbSession().boxFor(Solve.class).getAll()));
        } else if (solveTypeOfRemovedSolve == SOLVE_TYPE_BEST) {
            updateSolve(getNewBestSolves(App.getDbSession().boxFor(Solve.class).getAll()));
        }
    }

    private Solve getNewWorstSolve(List<Solve> solves) {
        if (solves.size() != 0) {
            return detectWorstSolve(solves);
        }

        return null;
    }

    private Solve detectWorstSolve(List<Solve> solves) {
        Solve newWorstSolve = solves.get(0);
        for (int i = 1; i < solves.size(); i++) {
            if (solves.get(i).getTime().getTarget().getFullTimeInMilliseconds()
                    > newWorstSolve.getTime().getTarget().getFullTimeInMilliseconds()) {
                newWorstSolve = solves.get(i);
            }
        }
        newWorstSolve.setSolveType(SOLVE_TYPE_WORST);

        return newWorstSolve;
    }

    private Solve getNewBestSolves(List<Solve> solves) {
        if (solves.size() != 0) {
            return detectBestSolve(solves);
        }

        return null;
    }

    private Solve detectBestSolve(List<Solve> solves) {
        Solve newBestSolve = solves.get(0);
        for (int i = 1; i < solves.size(); i++) {
            if (solves.get(i).getTime().getTarget().getFullTimeInMilliseconds()
                    < newBestSolve.getTime().getTarget().getFullTimeInMilliseconds()) {
                newBestSolve = solves.get(i);
            }
        }
        newBestSolve.setSolveType(SOLVE_TYPE_BEST);

        return newBestSolve;
    }

    @Override
    public void updateSolve(Solve solve) {
        if (solve != null) {
            App.getDbSession().boxFor(Solve.class).put(solve);
        }
    }

    @Override
    public List<Solve> getMiddleSolves() {
        List<Solve> middleSolveList =
                App.getDbSession().boxFor(Solve.class).query().equal(Solve_.solveType, SOLVE_TYPE_NORMAL).build().find();
        if (middleSolveList.size() == 0) {
            return null;
        } else {
            return middleSolveList;
        }
    }

    @Override
    public Solve getBestSolve() {
        List<Solve> bestSolveList =
                App.getDbSession().boxFor(Solve.class).query().equal(Solve_.solveType, SOLVE_TYPE_BEST).build().find();
        if (bestSolveList.size() == 0) {
            return null;
        } else {
            return bestSolveList.get(0);
        }
    }

    @Override
    public Solve getWorstSolve() {
        List<Solve> worstSolveList =
                App.getDbSession().boxFor(Solve.class).query().equal(Solve_.solveType, SOLVE_TYPE_WORST).build().find();
        if (worstSolveList.size() == 0) {
            return null;
        } else {
            return worstSolveList.get(0);
        }
    }
}
