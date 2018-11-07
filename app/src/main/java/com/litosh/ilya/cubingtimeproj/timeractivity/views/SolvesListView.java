package com.litosh.ilya.cubingtimeproj.timeractivity.views;

import com.litosh.ilya.cubingtimeproj.timeractivity.models.Solve;

import java.util.List;

/**
 * SolvesListView
 *
 * @author Ilya Litosh
 */
public interface SolvesListView {

    /**
     * Обновление списка сборок
     *
     * @param solves сборки
     */
    void updateSolvesList(List<Solve> solves);

    /**
     * Обновление списка сборок
     * после удаления сборки
     *
     * @param position позиция в списке сборок
     */
    void updateSolveListAfterDeletedSolve(int position);

}
