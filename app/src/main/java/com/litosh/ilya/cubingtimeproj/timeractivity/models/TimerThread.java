package com.litosh.ilya.cubingtimeproj.timeractivity.models;

import android.util.Log;

/**
 * TimerThread
 *
 * @author Ilya Litosh
 */
public class TimerThread extends Thread {

    private static final String LOG_TAG = "TimerThread";

    private static final long MAX_HOURS_VALUE = 60;
    private static final long MAX_MINUTES_VALUE = 60;
    private static final long MAX_SECONDS_VALUE = 60;
    private static final long MAX_MILLISECONDS_VALUE = 1000;
    private static final long MIN_VALUE = 0;

    private boolean isThreadAlive;
    private TimerThreadCallback mTimerThreadCallback;
    private Time mTime;

    public TimerThread(TimerThreadCallback timerThreadCallback) {
        mTimerThreadCallback = timerThreadCallback;
        mTime = new Time();
    }

    private long mStartIteration;
    private long mStopIteration;
    @Override
    public void run() {
        while (isThreadAlive) {
            try {
                mStartIteration = System.currentTimeMillis();
                Thread.sleep(5);
                mStopIteration = System.currentTimeMillis();
                mTime.setMilliseconds(mTime.getMilliseconds() + (mStopIteration - mStartIteration));
                if (mTime.getMilliseconds() >= MAX_MILLISECONDS_VALUE) {
                    mTime.setSeconds(mTime.getSeconds() + 1);
                    mTime.setMilliseconds(MIN_VALUE);
                }
                if (mTime.getSeconds() == MAX_SECONDS_VALUE) {
                    mTime.setMinutes(mTime.getMinutes() + 1);
                    mTime.setSeconds(MIN_VALUE);
                }
                if (mTime.getMinutes() == MAX_MINUTES_VALUE) {
                    mTime.setHours(mTime.getHours() + 1);
                    mTime.setMinutes(MIN_VALUE);
                }
                mTimerThreadCallback.onTimeRunning(mTime);
            } catch (InterruptedException e) {
                Log.e(LOG_TAG, e.toString());
            }
        }
        mTimerThreadCallback.onTimeRunning(mTime);
    }

    @Override
    public synchronized void start() {
        isThreadAlive = true;
        super.start();
    }

    public void stopThread() {
        isThreadAlive = false;
    }

    /**
     * TimerThreadCallback
     *
     * @author Ilya Litosh
     */
    public interface TimerThreadCallback {

        /**
         * Вызывается при работе потока таймера и возвращает текущее время
         *
         * @param time время таймера
         */
        void onTimeRunning(Time time);

    }
}
