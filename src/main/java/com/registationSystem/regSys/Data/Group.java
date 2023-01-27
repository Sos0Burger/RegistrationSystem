package com.registationSystem.regSys.Data;

import jakarta.persistence.*;
@Entity
@Table(name = "Groups")
public class Group {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "clientsIdSeq", sequenceName = "clients_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientsIdSeq")
    private final int id;
    @Column(name = "size")
    private final int size;
    @Column(name = "minAge")
    private final int minAge;
    @Column(name = "maxAge")
    private final int maxAge;

    public Group(int id, int size, int minAge, int maxAge){
        this.id = id;
        this.size = size;
        this.minAge = minAge;
        this.maxAge = maxAge;
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
}
