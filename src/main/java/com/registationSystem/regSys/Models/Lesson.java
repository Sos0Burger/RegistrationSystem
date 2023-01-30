package com.registationSystem.regSys.Models;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
@Entity
@Table(name = "Lessons")
public final class Lesson {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "LessonsIdSeq", sequenceName = "Lessons_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LessonsIdSeq")
    private int id;
    @Column(name = "time")
    private Time time;
    @Column(name = "date")
    private Date date;
    @Column(name = "coachid")
    private int coachId;
    @Column(name = "groupid")
    private int groupId;
    @Column(name = "isdone")
    private boolean isDone;

    public Lesson(){};

    public Lesson(int id, Time time, Date date, int coachID, int groupId, boolean isDone) {
        this.id = id;
        this.time = time;
        this.date = date;
        this.coachId = coachID;
        this.groupId = groupId;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public Time getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }

    public int getCoachId() {
        return coachId;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}