package com.litosh.ilya.cubingtimeproj.db;

import com.litosh.ilya.cubingtimeproj.db.models.User;

/**
 * DbCrud с базовыми методами для "общения"
 * с локальной БД
 *
 * Created by ilya_ on 20.06.2018.
 */

interface DbCrud {

    /**
     * Добавить User в БД
     *
     * @param user User-сущность для добавления
     */
    void addUser(User user);

    /**
     * Получить User из БД
     *
     */
    User getUser();

    /**
     * Обновить User в БД
     *
     * @param user User-сущность для обновления
     */
    void updateUser(User user);

    /**
     * Удалить User из БД
     *
     */
    void deleteUser();

}
