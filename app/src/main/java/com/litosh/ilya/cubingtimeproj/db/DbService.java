package com.litosh.ilya.cubingtimeproj.db;

import com.litosh.ilya.cubingtimeproj.app.App;
import com.litosh.ilya.cubingtimeproj.db.models.UserCache;
import com.litosh.ilya.cubingtimeproj.db.models.solves.Solve;
import com.litosh.ilya.cubingtimeproj.db.models.solves.Solve_;
import com.litosh.ilya.cubingtimeproj.db.models.solves.Time;
import com.litosh.ilya.cubingtimeproj.db.models.solves.Time_;

import java.util.List;

import io.objectbox.query.PropertyQuery;
import io.objectbox.query.QueryFilter;

/**
 * DbService репозиторий для доступа к локальной БД
 *
 * Created by ilya_ on 20.06.2018.
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
    public long addSolve(Solve solve) {
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

        return App.getDbSession().boxFor(Solve.class).put(solve);
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
        App.getDbSession().boxFor(Solve.class).remove(solve);
    }

    @Override
    public void updateSolve(Solve solve) {
        App.getDbSession().boxFor(Solve.class).put(solve);
    }

    @Override
    public List<Solve> getMiddleSolves() {
        List<Solve> middleSolveList =
                App.getDbSession().boxFor(Solve.class).query().equal(Solve_.solveType, 0).build().find();
        if (middleSolveList.size() == 0) {
            return null;
        } else {
            return middleSolveList;
        }
    }

    @Override
    public Solve getBestSolve() {
        List<Solve> bestSolveList =
                App.getDbSession().boxFor(Solve.class).query().equal(Solve_.solveType, 1).build().find();
        if (bestSolveList.size() == 0) {
            return null;
        } else {
            return bestSolveList.get(0);
        }
    }

    @Override
    public Solve getWorstSolve() {
        List<Solve> worstSolveList =
                App.getDbSession().boxFor(Solve.class).query().equal(Solve_.solveType, -1).build().find();
        if (worstSolveList.size() == 0) {
            return null;
        } else {
            return worstSolveList.get(0);
        }
    }
}
