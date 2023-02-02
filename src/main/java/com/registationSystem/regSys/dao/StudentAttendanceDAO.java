package com.registationSystem.regSys.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "students_attendance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentAttendanceDAO {
    @Id
    @Column(name = "attendance_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference("student-attendance")
    private StudentDAO studentDAO;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonBackReference("lesson-attendance")
    private LessonDAO lessonDAO;

    @Column(name = "attend")
    private Boolean attend;

    @Column(name = "warn")
    private Boolean warn;

}
