package com.registationSystem.regSys.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDAO {
    @Id
    @Column(name = "group_id")
    @SequenceGenerator(name = "GroupsIdSeq", sequenceName = "Groups_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GroupsIdSeq")
    private int id;
    @Column(name = "size")
    private int size;
    @Column(name = "min_age")
    private int minAge;
    @Column(name = "max_age")
    private int maxAge;
    @OneToMany(mappedBy = "group")
    @JsonManagedReference("group-student")
    private Set<StudentDAO> studentsList = new HashSet<>();

    @OneToMany(mappedBy = "group")
    @JsonManagedReference(value = "group-lesson")
    private Set<LessonDAO> lessonsList = new HashSet<>();

}
