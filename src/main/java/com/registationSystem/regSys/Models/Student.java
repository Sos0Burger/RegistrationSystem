package com.registationSystem.regSys.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Students")
public class Student {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "StudentsIdSeq", sequenceName = "Students_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "StudentsIdSeq")
    private int id;
    @Column(name = "firstname")
    private final String name;
    @Column(name = "surname")
    private final String surname;
    @Column(name = "age")
    private final int age;
    @Column(name = "absencecounter")
    private final int absenceCounter;
    @Column(name = "isstudying")
    private final boolean isStudying;

    public Student(int id, String name, String surname, int age, int absentCounter, boolean isStudying){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.absenceCounter = absentCounter;
        this.isStudying = isStudying;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getId() {
        return id;
    }

    public int getAbsentCounter() {
        return absenceCounter;
    }

    public boolean isStudying() {
        return isStudying;
    }

    public void setId(int id) {
        this.id = id;
    }
}
