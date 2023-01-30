package com.registationSystem.regSys.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "StudentsIdSeq", sequenceName = "students_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "StudentsIdSeq")
    private int id;
    @Column(name = "firstname")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    private int age;
    @Column(name = "groupid")
    int groupId;
    @Column(name = "absencecounter")
    private int absenceCounter;
    @Column(name = "isstudying")
    private boolean isStudying;
}
