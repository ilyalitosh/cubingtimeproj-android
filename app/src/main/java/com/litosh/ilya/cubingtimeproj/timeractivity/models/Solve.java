package com.litosh.ilya.cubingtimeproj.timeractivity.models;

public class Solve {

    private Scramble mScramble;
    private Time mTime;
    private boolean isPlusTwo;
    private boolean isDNF;

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
}
