package com.litosh.ilya.cubingtimeproj.timeractivity.models;

import com.ilya.litosh.RubiksCubeGraph;

/**
 * OnScrambleGenerate
 *
 * @author Ilya Litosh
 */
public interface OnScrambleGenerate {

    /**
     * Вызывается при генерации нового скрамбла
     *
     * @param scrambleGraph графическое представление сгенерированного скрамбла
     */
    void onGenerated(RubiksCubeGraph scrambleGraph);

}
