package com.litosh.ilya.cubingtimeproj.timeractivity.models;

/**
 * Solve
 *
 * @author Ilya Litosh
 */
public class Solve {

    private long mDbId;
    private Scramble mScramble;
    private Time mTime;
    private boolean isPlusTwo;
    private boolean isDNF;
    private String mDate;
    private int mSolveType;

    public long getDbId() {
        return mDbId;
    }

    public void setDbId(long dbId) {
        this.mDbId = dbId;
    }

    public Scramble getScramble() {
        return mScramble;
    }

    public void setScramble(Scramble mScramble) {
        this.mScramble = mScramble;
    }

    public Time getTime() {
        return mTime;
    }

    public void setTime(Time mTime) {
        this.mTime = mTime;
    }

    public boolean isPlusTwo() {
        return isPlusTwo;
    }

    public void setPlusTwo(boolean plusTwo) {
        isPlusTwo = plusTwo;
    }

    public boolean isDNF() {
        return isDNF;
    }

    public void setDNF(boolean DNF) {
        isDNF = DNF;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public int getSolveType() {
        return mSolveType;
    }

    public void setSolveType(int mSolveType) {
        this.mSolveType = mSolveType;
    }
}
