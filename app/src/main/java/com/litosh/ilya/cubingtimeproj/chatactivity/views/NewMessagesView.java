package com.litosh.ilya.cubingtimeproj.chatactivity.views;

import com.litosh.ilya.ct_sdk.models.messages.Message;

/**
 * NewMessagesView
 *
 * @author Ilya Litosh
 */
public interface NewMessagesView {

    /**
     * Вызывается для обновления окна
     * с сообщениями в приватном чате
     *
     * @param message сообщение
     */
    void updateMessagesList(Message message);

}
