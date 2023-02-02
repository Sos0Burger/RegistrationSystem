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
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDAO {
    @Id
    @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "size")
    private Integer size;
    @Column(name = "min_age")
    private Integer minAge;
    @Column(name = "max_age")
    private Integer maxAge;
    @OneToMany(mappedBy = "groupDAO", fetch = FetchType.LAZY)
    @JsonManagedReference("group-student")
    private Set<StudentDAO> studentsList = new HashSet<>();

    @OneToMany(mappedBy = "groupDAO", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "group-lesson")
    private Set<LessonDAO> lessonsList = new HashSet<>();

}
