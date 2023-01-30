package com.registationSystem.regSys.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Lesson {

    @Id
    @Column(name = "lesson_id")
    @SequenceGenerator(name = "LessonsIdSeq", sequenceName = "Lessons_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LessonsIdSeq")
    private int id;
    @Column(name = "time")
    private Time time;
    @Column(name = "date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonBackReference(value = "group-lesson")
    private Group group;
    @Column(name = "is_done")
    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    @JsonBackReference(value = "coach-lesson")
    private Coach coach;

    @OneToMany(mappedBy = "lesson")
    @JsonManagedReference(value = "lesson-attendance")
    private Set<StudentAttendance> attendanceList = new HashSet<>();

}