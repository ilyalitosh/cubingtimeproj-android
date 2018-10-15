package com.litosh.ilya.cubingtimeproj.db;

import com.litosh.ilya.cubingtimeproj.db.models.solves.Solve;

import java.util.List;

/**
 * DbSolveCrud
 *
 * @author Ilya Litosh
 */
public interface DbSolveCrud {

    /**
     * Добавляет сущность в БД
     *
     * @param solve сущность
     */
    void addSolve(Solve solve);

    /**
     * Получает сущность из БД
     *
     */
    List<Solve> getSolves();

    /**
     * Удаляет сущность из БД
     *
     * @param solve сущность
     */
    void removeSolve(Solve solve);

    /**
     * Обновляет сущноть в БД
     *
     * @param solve сущность
     */
    void updateSolve(Solve solve);

}
