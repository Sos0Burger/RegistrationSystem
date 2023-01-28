package com.registationSystem.regSys.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Groups")
public class Group {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "GroupsIdSeq", sequenceName = "Groups_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GroupsIdSeq")
    private int id;
    @Column(name = "size")
    private int size;
    @Column(name = "minage")
    private int minAge;
    @Column(name = "maxage")
    private int maxAge;
    @Column(name = "studentcounter")
    private int studentCounter;

    public Group(){};

    public Group(int id, int size, int minAge, int maxAge, int studentCounter){
        this.id = id;
        this.size = size;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.studentCounter = studentCounter;
    }

    public int getId() {
        return id;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getSize() {
        return size;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudentCounter(int studentCounter) {
        this.studentCounter = studentCounter;
    }

    public int getStudentCounter() {
        return studentCounter;
    }
}
