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
@Table(name = "Coaches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoachDAO {

    @Id
    @Column(name = "coach_id")
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
    @OneToMany(mappedBy = "coach")
    @JsonManagedReference(value = "coach-lesson")
    private Set<LessonDAO> lessonDAOList = new HashSet<>();

}
