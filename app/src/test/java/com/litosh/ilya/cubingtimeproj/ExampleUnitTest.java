package com.litosh.ilya.cubingtimeproj;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        int testAdd = 2 + 2;
        System.out.println("LOG_TEST: " + testAdd);
        assertEquals(4, testAdd);
    }
}