package com.registationSystem.regSys.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "Students")
public class Student {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "clientsIdSeq", sequenceName = "clients_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientsIdSeq")
    private final int id;
    @Column(name = "firstName")
    private final String name;
    @Column(name = "surname")
    private final String surname;
    @Column(name = "age")
    private final int age;
    @Column(name = "absentCounter")
    private final int absentCounter;
    @Column(name = "isStudying")
    private final boolean isStudying;

    public Student(int id, String name, String surname, int age, int absentCounter, boolean isStudying){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.absentCounter = absentCounter;
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
        return absentCounter;
    }

    public boolean isStudying() {
        return isStudying;
    }
}
