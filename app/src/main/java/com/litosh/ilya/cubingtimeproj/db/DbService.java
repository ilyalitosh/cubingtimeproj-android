package com.litosh.ilya.cubingtimeproj.db;

import com.litosh.ilya.cubingtimeproj.app.App;
import com.litosh.ilya.cubingtimeproj.db.models.UserCache;

/**
 * DbService репозиторий для доступа к локальной БД
 *
 * Created by ilya_ on 20.06.2018.
 */

public class DbService implements DbCrud {

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
}
