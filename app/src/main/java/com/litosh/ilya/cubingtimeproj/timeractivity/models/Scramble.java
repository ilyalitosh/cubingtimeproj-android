package com.litosh.ilya.cubingtimeproj.timeractivity.models;

/**
 * Scramble
 *
 * @author Ilya Litosh
 */
public class Scramble {

    private String mScramble;

    private Scramble() {

    }

    public static Scramble createScramble(String scrambleString) {
        Scramble scramble = new Scramble();
        scramble.setScramble(scrambleString);

        return scramble;
    }

    public String getScramble() {
        return mScramble;
    }

    public void setScramble(String mScramble) {
        this.mScramble = mScramble;
    }
}
