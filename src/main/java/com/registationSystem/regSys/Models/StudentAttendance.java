package com.registationSystem.regSys.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "students_attendance")
public class StudentAttendance {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "Students_attendanceIdSeq", sequenceName = "student_attendance_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Students_attendanceIdSeq")
    private int id;

    @Column(name = "student_id")
    private int student_id;

    @Column(name = "attend")
    private boolean attend;

    @Column(name = "warn")
    private boolean warn;

    public StudentAttendance(){}
    public StudentAttendance(int id, int student_id, boolean attend, boolean warn){
        this.id = id;
        this.student_id = student_id;
        this.attend = attend;
        this.warn = warn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public boolean isAttend() {
        return attend;
    }

    public void setAttend(boolean attend) {
        this.attend = attend;
    }

    public boolean isWarn() {
        return warn;
    }

    public void setWarn(boolean warn) {
        this.warn = warn;
    }
}
