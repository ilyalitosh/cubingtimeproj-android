package com.litosh.ilya.cubingtimeproj.db.models;

import com.litosh.ilya.cubingtimeproj.db.models.solves.Solve;
import com.litosh.ilya.cubingtimeproj.db.models.solves.Time;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.Scramble;

/**
 * DbConverter
 * конвертер БД сущностей
 *
 * @author Ilya Litosh
 */
public class DbConverter {

    /**
     * Конвертирование в сборку БД
     *
     * @param mSolve сборка
     */
    public static Solve toDbSolve(com.litosh.ilya.cubingtimeproj.timeractivity.models.Solve mSolve) {
        Solve dbSolve = new Solve();
        dbSolve.setScramble(mSolve.getScramble().getScramble());
        dbSolve.getTime().setTarget(toDbTime(mSolve.getTime()));

        return dbSolve;
    }

    /**
     * Конвертирование в время БД
     *
     * @param mTime время
     */
    public static Time toDbTime(com.litosh.ilya.cubingtimeproj.timeractivity.models.Time mTime) {
        Time dbTime = new Time();
        dbTime.setHours(mTime.getHours());
        dbTime.setMinutes(mTime.getMinutes());
        dbTime.setSeconds(mTime.getSeconds());
        dbTime.setMilliseconds(mTime.getMilliseconds());

        return dbTime;
    }

    /**
     * Конвертирование сборки из БД
     *
     * @param dbSolve сборка из БД
     */
    public static com.litosh.ilya.cubingtimeproj.timeractivity.models.Solve toSolve(Solve dbSolve) {
        com.litosh.ilya.cubingtimeproj.timeractivity.models.Solve solve =
                new com.litosh.ilya.cubingtimeproj.timeractivity.models.Solve();
        solve.setTime(toTime(dbSolve.getTime().getTarget()));
        solve.setScramble(Scramble.createScramble(dbSolve.getScramble()));

        return solve;
    }

    /**
     * Конвертирвоание времени из БД
     *
     * @param dbTime время из БД
     */
    public static com.litosh.ilya.cubingtimeproj.timeractivity.models.Time toTime(Time dbTime) {
        com.litosh.ilya.cubingtimeproj.timeractivity.models.Time time =
                new com.litosh.ilya.cubingtimeproj.timeractivity.models.Time();
        time.setHours(dbTime.getHours());
        time.setMinutes(dbTime.getMinutes());
        time.setSeconds(dbTime.getSeconds());
        time.setMilliseconds(dbTime.getMilliseconds());

        return time;
    }

}
