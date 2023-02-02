package com.registationSystem.regSys;


import com.registationSystem.regSys.dao.*;
import com.registationSystem.regSys.controller.controllerImpl.*;
import com.registationSystem.regSys.dto.rs.*;

public class Converter {
    public static StudentDAO studentDTOToStudentDAO(RsStudentDTO rsStudentDTO, StudentsControllerImpl studentsControllerImpl){
        return new StudentDAO(
                rsStudentDTO.getId(),
                rsStudentDTO.getFirstName(),
                rsStudentDTO.getSurname(),
                rsStudentDTO.getAge(),
                studentsControllerImpl.getGroupService().read(rsStudentDTO.getGroupId()),
                studentsControllerImpl.getStudentService().read(rsStudentDTO.getId())==null?
                        null:
                        studentsControllerImpl.getStudentService().read(rsStudentDTO.getId()).getStudentAttendanceDAOList()
        );
    }
    public static RsStudentDTO studentEntityToStudentModel(StudentDAO studentDAO){
        return new RsStudentDTO(studentDAO.getId(), studentDAO.getName(), studentDAO.getSurname(), studentDAO.getAge(),
                studentDAO.getGroupDAO()==null?-1: studentDAO.getGroupDAO().getId());
    }


    public static GroupDAO groupModelToGroupEntity(RsGroupDTO rsGroupDTO, GroupsControllerImpl groupsControllerImpl){
       return new GroupDAO(rsGroupDTO.getId(), rsGroupDTO.getSize(), rsGroupDTO.getMinAge(), rsGroupDTO.getMaxAge(),
               groupsControllerImpl.getGroupService().read(rsGroupDTO.getId())==null?
                       null:
                       groupsControllerImpl.getGroupService().read(rsGroupDTO.getId()).getStudentsList(),
               groupsControllerImpl.getGroupService().read(rsGroupDTO.getId())==null?
                       null:
                       groupsControllerImpl.getGroupService().read(rsGroupDTO.getId()).getLessonsList()

       );
    }
    public static RsGroupDTO groupEntityToGroupModel(GroupDAO groupDAO){
        return new RsGroupDTO(groupDAO.getId(), groupDAO.getSize(), groupDAO.getMinAge(), groupDAO.getMaxAge());
    }

    public static LessonDAO lessonModelToLessonEntity(RsLessonDTO rsLessonDTO, LessonsControllerImpl lessonsControllerImpl){
        return new LessonDAO(rsLessonDTO.getId(), rsLessonDTO.getTime(), rsLessonDTO.getDate(), rsLessonDTO.isDone(),
                lessonsControllerImpl.getLessonService().read(rsLessonDTO.getId())==null?
                        null:
                        lessonsControllerImpl.getLessonService().read(rsLessonDTO.getId()).getGroupDAO(),
                lessonsControllerImpl.getLessonService().read(rsLessonDTO.getId())==null?
                        null:
                        lessonsControllerImpl.getLessonService().read(rsLessonDTO.getId()).getCoachDAO(),
                lessonsControllerImpl.getLessonService().read(rsLessonDTO.getId())==null?
                        null:
                        lessonsControllerImpl.getLessonService().read((rsLessonDTO.getId())).getAttendanceList()
                );
    }
    public static RsLessonDTO lessonEntityToLessonModel(LessonDAO lessonDAO){

        return new RsLessonDTO(lessonDAO.getId(), lessonDAO.getTime(), lessonDAO.getDate(), lessonDAO.getGroupDAO().getId(), lessonDAO.isDone(),
                lessonDAO.getCoachDAO().getName()+"\s"+ lessonDAO.getCoachDAO().getSurname());
    }


    public static CoachDAO coachModelToCoachEntity(RsCoachDTO rsCoachDTO, CoachesControllerImpl coachesControllerImpl){
        return new CoachDAO(rsCoachDTO.getId(), rsCoachDTO.getName(), rsCoachDTO.getSurname(), rsCoachDTO.getPhoneNumber(), rsCoachDTO.getEmail(),
                coachesControllerImpl.getCoachService().read(rsCoachDTO.getId())==null?
                        null:
                        coachesControllerImpl.getCoachService().read(rsCoachDTO.getId()).getLessonDAOList()
        );
    }
    public static RsCoachDTO coachToCoachModel(CoachDAO coachDAO){
        return new RsCoachDTO(coachDAO.getId(), coachDAO.getName(), coachDAO.getSurname(), coachDAO.getPhone_number(), coachDAO.getPhone_number());
    }


    public static StudentAttendanceDAO studentAttendanceModelToStudentAttendanceEntity(RsStudentAttendanceDTO rsStudentAttendanceDTO, StudentAttendancesControllerImpl studentAttendancesControllerImpl){
        return new StudentAttendanceDAO(rsStudentAttendanceDTO.getId(),
                studentAttendancesControllerImpl.getStudentService().read(rsStudentAttendanceDTO.getLessonId())==null?
                        null:
                        studentAttendancesControllerImpl.getStudentService().read(rsStudentAttendanceDTO.getStudentId()),
                studentAttendancesControllerImpl.getLessonService().read(rsStudentAttendanceDTO.getLessonId())==null?
                        null:
                        studentAttendancesControllerImpl.getLessonService().read(rsStudentAttendanceDTO.getLessonId()),
                rsStudentAttendanceDTO.isAttend(), rsStudentAttendanceDTO.isWarn()
        );
    }
    public static RsStudentAttendanceDTO studentAttendanceEntityToStudentAttendanceModel(StudentAttendanceDAO studentAttendanceDAO){
        return new RsStudentAttendanceDTO(studentAttendanceDAO.getId(),
                studentAttendanceDAO.getStudentDAO().getId(),
                studentAttendanceDAO.getLessonDAO().getId(),
                studentAttendanceDAO.getStudentDAO().getName()+"\s"+ studentAttendanceDAO.getStudentDAO().getSurname(),
                studentAttendanceDAO.getLessonDAO().getDate(),
                studentAttendanceDAO.getLessonDAO().getTime(),
                studentAttendanceDAO.isAttend(),
                studentAttendanceDAO.isWarn()
        );
    }
}
