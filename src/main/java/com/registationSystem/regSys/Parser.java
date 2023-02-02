package com.registationSystem.regSys;


import com.registationSystem.regSys.Controllers.*;
import com.registationSystem.regSys.Entities.*;
import com.registationSystem.regSys.Models.*;
import com.registationSystem.regSys.Services.StudentAttendanceService;

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


    public static StudentAttendance studentAttendanceModelToStudentAttendanceEntity(StudentAttendanceModel studentAttendanceModel, StudentAttendancesController studentAttendancesController){
        return new StudentAttendance(studentAttendanceModel.getId(),
                studentAttendancesController.getStudentService().read(studentAttendanceModel.getLessonId())==null?
                        null:
                        studentAttendancesController.getStudentService().read(studentAttendanceModel.getStudentId()),
                studentAttendancesController.getLessonService().read(studentAttendanceModel.getLessonId())==null?
                        null:
                        studentAttendancesController.getLessonService().read(studentAttendanceModel.getLessonId()),
                studentAttendanceModel.isAttend(), studentAttendanceModel.isWarn()
        );
    }
    public static StudentAttendanceModel studentAttendanceEntityToStudentAttendanceModel(StudentAttendance studentAttendance){
        return new StudentAttendanceModel(studentAttendance.getId(),
                studentAttendance.getStudent().getId(),
                studentAttendance.getLesson().getId(),
                studentAttendance.getStudent().getName()+"\s"+studentAttendance.getStudent().getSurname(),
                studentAttendance.getLesson().getDate(),
                studentAttendance.getLesson().getTime(),
                studentAttendance.isAttend(),
                studentAttendance.isWarn()
        );
    }
}
