package com.registationSystem.regSys.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "Coaches")

public final class Coach {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "clientsIdSeq", sequenceName = "clients_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientsIdSeq")
    private final int id;
    @Column(name = "firstName")
    private final String name;
    @Column(name = "surname")
    private final String surname;

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

}
