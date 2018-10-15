package com.litosh.ilya.cubingtimeproj.db;

import com.litosh.ilya.cubingtimeproj.app.App;
import com.litosh.ilya.cubingtimeproj.db.models.UserCache;
import com.litosh.ilya.cubingtimeproj.db.models.solves.Solve;

import java.util.List;

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


    @Override
    public void addSolve(Solve solve) {
        App.getDbSession().boxFor(Solve.class).put(solve);
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
}
