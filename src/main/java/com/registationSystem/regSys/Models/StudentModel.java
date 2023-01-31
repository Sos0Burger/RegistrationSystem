package com.registationSystem.regSys.Models;

import com.registationSystem.regSys.Entities.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {
    private int id;
    private String firstName;
    private String surname;
    private int age;
    private int groupId;
    public static StudentModel parseStudentModel(Student student){
        return new StudentModel(student.getId(), student.getName(), student.getSurname(), student.getAge(), student.getGroup().getId());
    }
}
