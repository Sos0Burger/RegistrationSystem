package com.registationSystem.regSys.rest;

import com.registationSystem.regSys.dto.rq.RqStudentDTO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import com.registationSystem.regSys.dto.rs.RsStudentDTO;
import com.registationSystem.regSys.exception.CreationException;
import com.registationSystem.regSys.exception.FindException;
import com.registationSystem.regSys.exception.UpdateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/students")
public interface StudentApi {
    @Operation(summary = "Создание студента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Успешно создан"),
            @ApiResponse(responseCode = "404", description = "Группа не найдена")
    })
    @PostMapping
    ResponseEntity<?> create(@Validated @RequestBody RqStudentDTO rqStudentDTO) throws CreationException;

    @Operation(summary = "Обновление данных студента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно обновлён"),
            @ApiResponse(responseCode = "404", description = "Группа не найдена"),
            @ApiResponse(responseCode = "404", description = "Студент не найден")
    })
    @PutMapping
    ResponseEntity<?> update(@Validated @RequestBody RqStudentDTO rqStudentDTO, @PathVariable(name = "id") int id) throws UpdateException;

    @Operation(summary = "Получение данных всех студентов")
    @ApiResponse(responseCode = "200", description = "Данные успешно получены")
    @GetMapping
    ResponseEntity<List<RsStudentDTO>> readAll();

    @Operation(summary = "Получение расписания по ID студента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные успешно получены"),
            @ApiResponse(responseCode = "404", description = "Студент не найден")
    })
    @GetMapping("/{id}/schedule")
    ResponseEntity<List<RsLessonDTO>> getSchedule(@PathVariable(name = "id") int id) throws FindException;

    @Operation(summary = "Получение данных по ID студента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные успешно получены"),
            @ApiResponse(responseCode = "404", description = "Студент не найден")
    })
    @GetMapping("/{id}")
    ResponseEntity<RsStudentDTO> findById(@PathVariable(name = "id") int id) throws FindException;

}
