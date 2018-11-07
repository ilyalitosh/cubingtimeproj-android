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
    public static Solve toDbSolve(com.litosh.ilya.cubingtimeproj.timeractivity.models.Solve mSolve, boolean isNewSolve) {
        Solve dbSolve = new Solve();
        if (!isNewSolve) {
            dbSolve.setId(mSolve.getDbId());
        }
        dbSolve.getTime().setTarget(toDbTime(mSolve.getTime(), isNewSolve));
        dbSolve.setScramble(mSolve.getScramble().getScramble());
        dbSolve.setDate(mSolve.getDate());
        dbSolve.setSolveType(mSolve.getSolveType());

        return dbSolve;
    }

    /**
     * Конвертирование в время БД
     *
     * @param mTime время
     */
    public static Time toDbTime(com.litosh.ilya.cubingtimeproj.timeractivity.models.Time mTime, boolean isNewTime) {
        Time dbTime = new Time();
        if (!isNewTime) {
            dbTime.setId(mTime.getDbId());
        }
        dbTime.setHours(mTime.getHours());
        dbTime.setMinutes(mTime.getMinutes());
        dbTime.setSeconds(mTime.getSeconds());
        dbTime.setMilliseconds(mTime.getMilliseconds());
        dbTime.setFullTimeInMilliseconds(mTime.getFullTimeInMilliseconds());

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
        solve.setDbId(dbSolve.getId());
        solve.setTime(toTime(dbSolve.getTime().getTarget()));
        solve.setScramble(Scramble.createScramble(dbSolve.getScramble()));
        solve.setDate(dbSolve.getDate());
        solve.setSolveType(dbSolve.getSolveType());

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
        time.setDbId(dbTime.getId());
        time.setHours(dbTime.getHours());
        time.setMinutes(dbTime.getMinutes());
        time.setSeconds(dbTime.getSeconds());
        time.setMilliseconds(dbTime.getMilliseconds());
        time.setFullTimeInMilliseconds(dbTime.getFullTimeInMilliseconds());

        return time;
    }

}
