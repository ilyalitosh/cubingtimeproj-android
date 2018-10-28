package com.litosh.ilya.cubingtimeproj.db.models.solves;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

/**
 * Solve
 * Сущность одной сборки
 * для локальной БД
 *
 * @author Ilya Litosh
 */
@Entity
public class Solve {

    @Id
    private long id;
    private ToOne<Time> time;
    private String scramble;
    private String date;
    private int solveType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ToOne<Time> getTime() {
        return time;
    }

    public void setTime(ToOne<Time> time) {
        this.time = time;
    }

    public String getScramble() {
        return scramble;
    }

    public void setScramble(String scramble) {
        this.scramble = scramble;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String mDate) {
        this.date = mDate;
    }

    public int getSolveType() {
        return solveType;
    }

    public void setSolveType(int solveType) {
        this.solveType = solveType;
    }
}
