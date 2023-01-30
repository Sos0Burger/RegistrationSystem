package com.registationSystem.regSys.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Coaches")

public class Coach {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "CoachesIdSeq", sequenceName = "coaches_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CoachesIdSeq")
    private int id;
    @Column(name = "firstname")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "email")
    private String email;

    public Coach(){};
    public Coach(int id, String name, String surname, String phone_number, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone_number = phone_number;
        this.email = email;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
