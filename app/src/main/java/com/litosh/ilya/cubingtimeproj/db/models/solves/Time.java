package com.litosh.ilya.cubingtimeproj.db.models.solves;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Time
 * сущность времени сборки
 *
 * @author Ilya Litosh
 */
@Entity
public class Time {

    @Id
    private long id;
    private long hours;
    private long minutes;
    private long seconds;
    private long milliseconds;
    private long fullTimeInMilliseconds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHours() {
        return hours;
    }

    public void setHours(long hours) {
        this.hours = hours;
    }

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public long getFullTimeInMilliseconds() {
        return fullTimeInMilliseconds;
    }

    public void setFullTimeInMilliseconds(long fullTimeInMilliseconds) {
        this.fullTimeInMilliseconds = fullTimeInMilliseconds;
    }
}
