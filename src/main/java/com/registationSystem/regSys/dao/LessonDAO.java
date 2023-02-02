package com.registationSystem.regSys.dao;

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
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class LessonDAO {

    @Id
    @Column(name = "lesson_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "time")
    private Time time;
    @Column(name = "date")
    private Date date;
    @Column(name = "is_done")
    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonBackReference(value = "group-lesson")
    private GroupDAO groupDAO;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    @JsonBackReference(value = "coach-lesson")
    private CoachDAO coachDAO;

    @OneToMany(mappedBy = "lessonDAO", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "lesson-attendance")
    private Set<StudentAttendanceDAO> attendanceList = new HashSet<>();

}