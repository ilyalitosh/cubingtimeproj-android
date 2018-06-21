package com.litosh.ilya.cubingtimeproj.db;

import com.litosh.ilya.cubingtimeproj.db.models.UserCache;

/**
 * DbCrud с базовыми методами для "общения"
 * с локальной БД
 *
 * Created by ilya_ on 20.06.2018.
 */

interface DbCrud {

    /**
     * Добавить UserCache в БД
     *
     * @param userCache UserCache-сущность для добавления
     */
    void addUser(UserCache userCache);

    /**
     * Получить UserCache из БД
     *
     */
    UserCache getUser();

    /**
     * Обновить UserCache в БД
     *
     * @param userCache UserCache-сущность для обновления
     */
    void updateUser(UserCache userCache);

    /**
     * Удалить UserCache из БД
     *
     */
    void deleteUser();

}
