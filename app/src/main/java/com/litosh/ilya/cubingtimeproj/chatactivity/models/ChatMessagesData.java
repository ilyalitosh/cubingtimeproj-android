package com.litosh.ilya.cubingtimeproj.chatactivity.models;

/**
 * ChatMessagesData
 *
 * Created by ilya_ on 25.06.2018.
 */

public class ChatMessagesData {

    public static final int MESSAGE_OTHER_USER = 1;
    public static final int MESSAGE_ME = 2;
    private String mChatId;
    private String mChatName;
    private String mChatImageUrl;

    public String getChatId() {
        return mChatId;
    }

    public void setChatId(String mChatId) {
        this.mChatId = mChatId;
    }

    public String getChatName() {
        return mChatName;
    }

    public void setChatName(String mChatName) {
        this.mChatName = mChatName;
    }

    public String getChatImageUrl() {
        return mChatImageUrl;
    }

    public void setChatImageUrl(String mChatImageUrl) {
        this.mChatImageUrl = mChatImageUrl;
    }
}
