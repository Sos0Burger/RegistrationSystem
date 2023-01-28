package com.registationSystem.regSys.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Coaches")

public class Coach {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "Coaches", sequenceName = "Coaches_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CoachesIdSeq")
    private int id;
    @Column(name = "firstname")
    private String name;
    @Column(name = "surname")
    private String surname;

    public Coach(){};
    public Coach(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(int id) {
        this.id = id;
    }
}
