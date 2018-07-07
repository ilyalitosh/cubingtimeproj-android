package com.litosh.ilya.cubingtimeproj.chatfragment.views;

import com.arellomobile.mvp.MvpView;
import com.litosh.ilya.ct_sdk.models.messages.Message;

/**
 * NewMessagesView
 *
 */
public interface NewMessagesView extends MvpView {

    /**
     * Вызывается для обновления окна
     * с сообщениями в приватном чате
     *
     * @param message сообщение
     */
    void updateMessagesList(Message message);

}
