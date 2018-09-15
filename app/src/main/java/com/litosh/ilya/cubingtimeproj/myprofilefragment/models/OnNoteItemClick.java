package com.litosh.ilya.cubingtimeproj.myprofilefragment.models;

import com.litosh.ilya.ct_sdk.models.profile.Note;

/**
 * OnNoteItemClick
 * Слушатель клика по записи
 *
 * @author Ilya Litosh
 */
public interface OnNoteItemClick {

    /**
     * Вызывается при клике по записи
     *
     * @param note сущность Note
     * @param position позиция в списке
     */
    void onClick(Note note, int position);

}
