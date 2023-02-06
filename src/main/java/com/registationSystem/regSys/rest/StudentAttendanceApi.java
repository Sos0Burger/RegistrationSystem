package com.registationSystem.regSys.rest;

import com.registationSystem.regSys.dto.rq.RqStudentAttendanceDTO;
import com.registationSystem.regSys.exception.CreationException;
import com.registationSystem.regSys.exception.UpdateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/studentAttendances")
public interface StudentAttendanceApi {
    @Operation(summary = "Создание посещаемости для студента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Успешно создано"),
            @ApiResponse(responseCode = "404", description = "Студент не найден"),
            @ApiResponse(responseCode = "404", description = "Занятие не найдено")
    })
    @PostMapping
    ResponseEntity<?> create(@RequestBody RqStudentAttendanceDTO rqStudentAttendanceDTO) throws CreationException;

    @Operation(summary = "Обновление данных посещаемости по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно изменено"),
            @ApiResponse(responseCode = "404", description = "Посещаемость не найдена")

    })
    @PutMapping("/{id}")
    ResponseEntity<?> update(@RequestBody RqStudentAttendanceDTO rqStudentAttendanceDTO, @PathVariable int id) throws UpdateException;
}
