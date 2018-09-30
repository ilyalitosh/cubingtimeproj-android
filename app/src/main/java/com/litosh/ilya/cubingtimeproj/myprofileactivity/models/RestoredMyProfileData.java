package com.litosh.ilya.cubingtimeproj.myprofileactivity.models;

import com.litosh.ilya.ct_sdk.models.profile.Note;
import com.litosh.ilya.ct_sdk.models.profile.User;
import com.litosh.ilya.ct_sdk.models.profile.Wall;

import java.util.Stack;

/**
 * RestoredMyProfileData
 * data-модель с восстановленными
 * данными профиля пользователя
 *
 * @author Ilya Litosh
 */
public class RestoredMyProfileData {

    private Stack<Note> mRestoredNote = new Stack<>();
    private Stack<Wall> mRestoredWall = new Stack<>();
    private Stack<User> mRestoredUser = new Stack<>();
    private int mPositionRestored = -1;
    private int mNotePositionRestored = -1;

    public Stack<Note> getRestoredNote() {
        return mRestoredNote;
    }

    public void setRestoredNote(Stack<Note> mRestoredNote) {
        this.mRestoredNote = mRestoredNote;
    }

    public Stack<Wall> getRestoredWall() {
        return mRestoredWall;
    }

    public void setRestoredWall(Stack<Wall> mRestoredWall) {
        this.mRestoredWall = mRestoredWall;
    }

    public Stack<User> getRestoredUser() {
        return mRestoredUser;
    }

    public void setRestoredUser(Stack<User> mRestoredUser) {
        this.mRestoredUser = mRestoredUser;
    }

    public int getPositionRestored() {
        return mPositionRestored;
    }

    public void setPositionRestored(int mPositionRestored) {
        this.mPositionRestored = mPositionRestored;
    }

    public int getNotePositionRestored() {
        return mPositionRestored - ProfileListData.NUMBER_STATIC_ITEMS;
    }

    /**
     * Очищает стеки восстановленных данных
     *
     */
    public void clearData() {
        mPositionRestored = -1;
        mRestoredNote.clear();
        mRestoredUser.clear();
        mRestoredWall.clear();
    }

}
