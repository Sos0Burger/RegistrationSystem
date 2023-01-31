package com.registationSystem.regSys;


import com.registationSystem.regSys.Controllers.GroupsController;
import com.registationSystem.regSys.Controllers.StudentsController;
import com.registationSystem.regSys.Entities.Group;
import com.registationSystem.regSys.Entities.Student;
import com.registationSystem.regSys.Models.GroupModel;
import com.registationSystem.regSys.Models.StudentModel;

public class Parser {
    public static Student studentModelToStudentEntity(StudentModel studentModel, StudentsController studentsController){
        return new Student(
                studentModel.getId(),
                studentModel.getFirstName(),
                studentModel.getSurname(),
                studentModel.getAge(),
                studentsController.getGroupService().read(studentModel.getGroupId()),
                studentsController.getStudentService().read(studentModel.getId())==null?
                        null:
                        studentsController.getStudentService().read(studentModel.getId()).getStudentAttendanceList()
        );
    }
    public static StudentModel studentEntityToStudentModel(Student student){
        return new StudentModel(student.getId(), student.getName(), student.getSurname(), student.getAge(),
                student.getGroup()==null?-1:student.getGroup().getId());
    }


    public static Group groupModelToGroupEntity(GroupModel groupModel, GroupsController groupsController){
       return new Group(groupModel.getId(),groupModel.getSize(),groupModel.getMinAge(),groupModel.getMaxAge(),
               groupsController.getGroupService().read(groupModel.getId())==null?
                       null:
                       groupsController.getGroupService().read(groupModel.getId()).getStudentsList(),
               groupsController.getGroupService().read(groupModel.getId())==null?
                       null:
                       groupsController.getGroupService().read(groupModel.getId()).getLessonsList()

       );
    }
    public static GroupModel groupEntityToGroupModel(Group group){
        return new GroupModel(group.getId(), group.getSize(), group.getMinAge(), group.getMaxAge());
    }
}
