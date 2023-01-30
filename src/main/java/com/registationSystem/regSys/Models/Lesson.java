package com.registationSystem.regSys.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
@Entity
@Table(name = "Lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}