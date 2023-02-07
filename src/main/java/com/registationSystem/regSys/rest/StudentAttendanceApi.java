package com.registationSystem.regSys.rest;

import com.registationSystem.regSys.dto.rq.RqStudentAttendanceDTO;
import com.registationSystem.regSys.dto.rs.RsStudentAttendanceDTO;
import com.registationSystem.regSys.exception.CreationException;
import com.registationSystem.regSys.exception.UpdateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/studentAttendances")
public interface StudentAttendanceApi {
    @Operation(summary = "Создание посещаемости для студента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Успешно создано"),
            @ApiResponse(responseCode = "404", description = "Студент не найден, занятие не найдено"),
            @ApiResponse(responseCode = "400", description = "Данные не прошли валидацию")
    })
    @PostMapping
    ResponseEntity<?> create(@Validated @RequestBody RqStudentAttendanceDTO rqStudentAttendanceDTO) throws CreationException;

    @Operation(summary = "Обновление данных посещаемости по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно изменено"),
            @ApiResponse(responseCode = "404", description = "Посещаемость не найдена"),
            @ApiResponse(responseCode = "400", description = "Данные не прошли валидацию")
    })
    @PutMapping("/{id}")
    ResponseEntity<?> update(@Validated @RequestBody RqStudentAttendanceDTO rqStudentAttendanceDTO, @PathVariable int id) throws UpdateException;
    @Operation(summary = "Получение всей посещаемости")
    @ApiResponse(responseCode = "200", description = "Данные успешно получены")
    @GetMapping
    ResponseEntity<List<RsStudentAttendanceDTO>> findAll();
}
