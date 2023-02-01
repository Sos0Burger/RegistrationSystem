package com.registationSystem.regSys;


import com.registationSystem.regSys.Controllers.CoachesController;
import com.registationSystem.regSys.Controllers.GroupsController;
import com.registationSystem.regSys.Controllers.LessonsController;
import com.registationSystem.regSys.Controllers.StudentsController;
import com.registationSystem.regSys.Entities.Coach;
import com.registationSystem.regSys.Entities.Group;
import com.registationSystem.regSys.Entities.Lesson;
import com.registationSystem.regSys.Entities.Student;
import com.registationSystem.regSys.Models.CoachModel;
import com.registationSystem.regSys.Models.GroupModel;
import com.registationSystem.regSys.Models.LessonModel;
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

    public static Lesson lessonModelToLessonEntity(LessonModel lessonModel, LessonsController lessonsController){
        return new Lesson(lessonModel.getId(), lessonModel.getTime(), lessonModel.getDate(), lessonModel.isDone(),
                lessonsController.getLessonService().read(lessonModel.getId())==null?
                        null:
                        lessonsController.getLessonService().read(lessonModel.getId()).getGroup(),
                lessonsController.getLessonService().read(lessonModel.getId())==null?
                        null:
                        lessonsController.getLessonService().read(lessonModel.getId()).getCoach(),
                lessonsController.getLessonService().read(lessonModel.getId())==null?
                        null:
                        lessonsController.getLessonService().read((lessonModel.getId())).getAttendanceList()
                );
    }
    public static LessonModel lessonEntityToLessonModel(Lesson lesson){
        return new LessonModel(lesson.getId(), lesson.getTime(), lesson.getDate(), lesson.getGroup().getId(), lesson.isDone(),
                lesson.getCoach().getName()+"\s"+lesson.getCoach().getSurname());
    }

    public static Coach coachModelToCoachEntity(CoachModel coachModel, CoachesController coachesController){
        return new Coach(coachModel.getId(), coachModel.getName(), coachModel.getSurname(), coachModel.getPhoneNumber(), coachModel.getEmail(),
                coachesController.getCoachService().read(coachModel.getId())==null?
                        null:
                        coachesController.getCoachService().read(coachModel.getId()).getLessonList()
        );
    }
    public static CoachModel coachToCoachModel(Coach coach){
        return new CoachModel(coach.getId(), coach.getName(), coach.getSurname(), coach.getPhone_number(), coach.getPhone_number());
    }
}
