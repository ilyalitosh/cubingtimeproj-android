package com.litosh.ilya.cubingtimeproj.timeractivity.models;

/**
 * Time
 * Модель для времени Timer
 *
 * @author Ilya Litosh
 */
public class Time {

    private long mDbId;
    private long mHours;
    private long mMinutes;
    private long mSeconds;
    private long mMilliseconds;
    private long mFullTimeInMilliseconds;

    public long getDbId() {
        return mDbId;
    }

    public void setDbId(long mDbId) {
        this.mDbId = mDbId;
    }

    public long getHours() {
        return mHours;
    }

    public String getHoursStringFormatted() {
        String hoursString = String.valueOf(mHours);
        if (hoursString.length() == 1) {
            hoursString = "0" + hoursString;
        }
        return hoursString;
    }

    public void setHours(long mHours) {
        this.mHours = mHours;
    }

    public long getMinutes() {
        return mMinutes;
    }

    public String getMinutesStringFormatted() {
        String minutesString = String.valueOf(mMinutes);
        if (minutesString.length() == 1) {
            minutesString = "0" + minutesString;
        }
        return minutesString;
    }

    public void setMinutes(long mMinutes) {
        this.mMinutes = mMinutes;
    }

    public long getSeconds() {
        return mSeconds;
    }

    public String getSecondsStringFormatted() {
        String secondsString = String.valueOf(mSeconds);
        if (secondsString.length() == 1) {
            secondsString = "0" + secondsString;
        }
        return secondsString;
    }

    public void setSeconds(long mSeconds) {
        this.mSeconds = mSeconds;
    }

    public long getMilliseconds() {
        return mMilliseconds;
    }

    public String getMillisecondsStringFormatted() {
        String millisecondsString = String.valueOf(mMilliseconds);
        if (millisecondsString.length() == 1) {
            millisecondsString = "00" + millisecondsString;
        } else if (millisecondsString.length() == 2) {
            millisecondsString = "0" + millisecondsString;
        }
        return millisecondsString;
    }

    public void setMilliseconds(long mMilliseconds) {
        this.mMilliseconds = mMilliseconds;
    }

    public long getFullTimeInMilliseconds() {
        return mFullTimeInMilliseconds;
    }

    public void setFullTimeInMilliseconds(long mFullTimeInMilliseconds) {
        this.mFullTimeInMilliseconds = mFullTimeInMilliseconds;
    }

    @Override
    public String toString() {
        return getHoursStringFormatted() + ":"
                + getMinutesStringFormatted() + ":"
                + getSecondsStringFormatted() + "."
                + getMillisecondsStringFormatted();
    }
}
