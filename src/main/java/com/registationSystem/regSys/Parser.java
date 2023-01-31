package com.registationSystem.regSys;


import com.registationSystem.regSys.Controllers.StudentController;
import com.registationSystem.regSys.Entities.Student;
import com.registationSystem.regSys.Models.StudentModel;

public class Parser {
    public static Student StudentModelToStudentEntity(StudentModel studentModel, StudentController studentController){
        return new Student(studentModel.getId(), studentModel.getFirstName(), studentModel.getSurname(), studentModel.getAge(),
                studentController.getGroupService().read(studentModel.getGroupId()),
                studentController.getStudentService().read(studentModel.getId()).getStudentAttendanceList());
    }
}
