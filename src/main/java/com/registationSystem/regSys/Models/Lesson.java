package com.registationSystem.regSys.Models;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
@Entity
@Table(name = "Lessons")
public final class Lesson {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "clientsIdSeq", sequenceName = "clients_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientsIdSeq")
    private int id;
    @Column(name = "time")
    private Time time;
    @Column(name = "date")
    private Date date;
    @Column(name = "coachId")
    private int coachId;
    @Column(name = "absentList")
    private int[] absentList;
    @Column(name = "isDone")
    private boolean isDone;

    public Lesson(int id, Time time, Date date, int coachID, int[] absentList, boolean isDone) {
        this.id = id;
        this.time = time;
        this.date = date;
        this.coachId = coachID;
        this.absentList = absentList;
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

    public int[] getAbsentList() {
        return absentList;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setAbsentList(int[] absentList) {
        this.absentList = absentList;
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
}