package com.registationSystem.regSys.Models;

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
public class StudentAttendance {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "Students_attendanceIdSeq", sequenceName = "student_attendance_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Students_attendanceIdSeq")
    private int id;

    @Column(name = "student")
    private int student;

    @Column(name = "attend")
    private boolean attend;

    @Column(name = "warn")
    private boolean warn;
}
