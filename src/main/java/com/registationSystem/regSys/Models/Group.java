package com.registationSystem.regSys.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
