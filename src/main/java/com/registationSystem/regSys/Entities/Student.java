package com.registationSystem.regSys.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @Column(name = "student_id")
    @SequenceGenerator(name = "StudentsIdSeq", sequenceName = "students_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "StudentsIdSeq")
    private int id;
    @Column(name = "firstname")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    private int age;
    @ManyToOne
    @JoinColumn(name="group_id")
    @JsonBackReference("group-student")
    private Group group;

    @OneToMany(mappedBy = "student")
    @JsonManagedReference(value = "student-attendance")
    private Set<StudentAttendance> studentAttendanceList = new HashSet<>();
}
