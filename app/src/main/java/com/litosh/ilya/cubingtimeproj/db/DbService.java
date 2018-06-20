package com.litosh.ilya.cubingtimeproj.db;

import com.litosh.ilya.cubingtimeproj.app.App;
import com.litosh.ilya.cubingtimeproj.db.models.User;

/**
 * DbService репозиторий для доступа к локальной БД
 *
 * Created by ilya_ on 20.06.2018.
 */

public class DbService implements DbCrud {

    private static final String TAG = "OBoxDbService";

    @Override
    public void addUser(User user) {
        App.getDbSession()
                .boxFor(User.class)
                .put(user);
    }

    @Override
    public User getUser() {
        return App.getDbSession()
                .boxFor(User.class)
                .get(1);
    }

    @Override
    public void updateUser(User user) {
        user.setId(1);
        App.getDbSession()
                .boxFor(User.class)
                .put(user);
    }

    @Override
    public void deleteUser() {
        App.getDbSession()
                .boxFor(User.class)
                .removeAll();
    }
}
