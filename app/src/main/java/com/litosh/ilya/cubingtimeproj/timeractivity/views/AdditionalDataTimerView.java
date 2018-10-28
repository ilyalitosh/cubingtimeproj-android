package com.litosh.ilya.cubingtimeproj.timeractivity.views;

import com.litosh.ilya.cubingtimeproj.timeractivity.models.AdditionalDataModel;

/**
 * AdditionalDataTimerView
 *
 * @author Ilya Litosh
 */
public interface AdditionalDataTimerView {

    /**
     * Вызывается для обновления TextView статистики
     *
     * @param additionalDataModel дата-модель
     */
    void updateAdditionalData(AdditionalDataModel additionalDataModel);

}
