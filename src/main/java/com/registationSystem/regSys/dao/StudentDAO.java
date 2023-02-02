package com.registationSystem.regSys.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDAO {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "firstname")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    private Integer age;
    @ManyToOne
    @JoinColumn(name="group_id")
    @JsonBackReference("group-student")
    private GroupDAO groupDAO;

    @OneToMany(mappedBy = "studentDAO", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "student-attendance")
    private Set<StudentAttendanceDAO> studentAttendanceDAOList = new HashSet<>();
}
