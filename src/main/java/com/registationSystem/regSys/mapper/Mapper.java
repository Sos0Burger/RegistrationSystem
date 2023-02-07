package com.registationSystem.regSys.mapper;


import com.registationSystem.regSys.dao.*;
import com.registationSystem.regSys.dto.rq.*;
import com.registationSystem.regSys.dto.rs.*;
import com.registationSystem.regSys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    static GroupService groupService;
    static CoachService coachService;
    static StudentService studentService;
    static LessonService lessonService;
    static StudentAttendanceService studentAttendanceService;

    @Autowired
    Mapper(GroupService groupService,
           CoachService coachService,
           StudentService studentService,
           LessonService lessonService,
           StudentAttendanceService studentAttendanceService) {
        Mapper.groupService = groupService;
        Mapper.coachService = coachService;
        Mapper.studentService = studentService;
        Mapper.lessonService = lessonService;
        Mapper.studentAttendanceService = studentAttendanceService;
    }

    public static StudentDAO studentDTOToStudentDAO(RqStudentDTO rqStudentDTO) {
        return new StudentDAO(
                null,
                rqStudentDTO.getFirstName(),
                rqStudentDTO.getSurname(),
                rqStudentDTO.getAge(),
                rqStudentDTO.getGroupId() == null ?
                        null :
                        groupService.read(rqStudentDTO.getGroupId()),
                null
        );
    }

    public static RsStudentDTO studentDAOToStudentDTO(StudentDAO studentDAO) {
        return new RsStudentDTO(studentDAO.getId(),
                studentDAO.getName(),
                studentDAO.getSurname(),
                studentDAO.getAge(),
                studentDAO.getGroupDAO() == null ?
                        null :
                        studentDAO.getGroupDAO().getId()
        );
    }


    public static GroupDAO groupDTOToGroupDAO(RqGroupDTO rqGroupDTO) {
        return new GroupDAO(null, rqGroupDTO.getSize(),
                rqGroupDTO.getMinAge(),
                rqGroupDTO.getMaxAge(),
                null,
                null
        );
    }

    public static RsGroupDTO groupDAOToGroupDTO(GroupDAO groupDAO) {
        return new RsGroupDTO(groupDAO.getId(),
                groupDAO.getSize(),
                groupDAO.getMinAge(),
                groupDAO.getMaxAge());
    }

    public static LessonDAO lessonDTOToLessonDAO(RqLessonDTO rqLessonDTO) {
        return new LessonDAO(null,
                rqLessonDTO.getTime(),
                rqLessonDTO.getDate(),
                false,
                groupService.read(rqLessonDTO.getGroupId()),
                coachService.read(rqLessonDTO.getCoachId()),
                null
        );
    }

    public static RsLessonDTO lessonDAOToLessonDTO(LessonDAO lessonDAO) {

        return new RsLessonDTO(lessonDAO.getId(),
                lessonDAO.getTime(),
                lessonDAO.getDate(),
                lessonDAO.getGroupDAO().getId(),
                lessonDAO.getIsDone(),
                lessonDAO.getCoachDAO().getName() + " " + lessonDAO.getCoachDAO().getSurname()
        );
    }


    public static CoachDAO coachDTOToCoachDAO(RqCoachDTO rqCoachDTO) {
        return new CoachDAO(null,
                rqCoachDTO.getName(),
                rqCoachDTO.getSurname(),
                rqCoachDTO.getPhoneNumber(),
                rqCoachDTO.getEmail(),
                null
        );
    }

    public static RsCoachDTO coachDAOToCoachDTO(CoachDAO coachDAO) {
        return new RsCoachDTO(coachDAO.getId(),
                coachDAO.getName(),
                coachDAO.getSurname(),
                coachDAO.getPhoneNumber(),
                coachDAO.getEmail()
        );
    }


    public static StudentAttendanceDAO studentAttendanceDTOToStudentAttendanceDAO(RqStudentAttendanceDTO rqStudentAttendanceDTO) {
        return new StudentAttendanceDAO(rqStudentAttendanceDTO.getId(),
                studentService.read(rqStudentAttendanceDTO.getStudentId()),
                lessonService.read(rqStudentAttendanceDTO.getLessonId()),
                rqStudentAttendanceDTO.getAttend(),
                rqStudentAttendanceDTO.getWarn()
        );
    }

    public static RsStudentAttendanceDTO studentAttendanceDAOToStudentAttendanceDTO(StudentAttendanceDAO studentAttendanceDAO) {
        return new RsStudentAttendanceDTO(studentAttendanceDAO.getId(),
                studentAttendanceDAO.getStudentDAO().getId(),
                studentAttendanceDAO.getLessonDAO().getId(),
                studentAttendanceDAO.getStudentDAO().getName() + " " + studentAttendanceDAO.getStudentDAO().getSurname(),
                studentAttendanceDAO.getLessonDAO().getDate(),
                studentAttendanceDAO.getLessonDAO().getTime(),
                studentAttendanceDAO.getAttend(),
                studentAttendanceDAO.getWarn()
        );
    }
}
