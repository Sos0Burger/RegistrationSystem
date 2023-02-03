package com.registationSystem.regSys.rest;

import com.registationSystem.regSys.dto.rq.RqStudentDTO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import com.registationSystem.regSys.dto.rs.RsStudentDTO;
import com.registationSystem.regSys.exception.ControllerException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/students")
public interface StudentApi {
    @Operation(summary = "Создание студента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно создан"),
            @ApiResponse(responseCode = "404", description = "Группа не найдена")
    })
    @PostMapping
    ResponseEntity<?> create(@RequestBody RqStudentDTO rqStudentDTO) throws ControllerException;

    @Operation(summary = "Обновление данных студента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно обновлён"),
            @ApiResponse(responseCode = "404", description = "Группа не найдена"),
            @ApiResponse(responseCode = "404", description = "Студент не найден")
    })
    @PutMapping
    ResponseEntity<?> update(@RequestBody RqStudentDTO rqStudentDTO, @PathVariable(name = "id")int id) throws ControllerException;
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
    ResponseEntity<List<RsLessonDTO>> getSchedule(@PathVariable(name = "id")int id);
    @Operation(summary = "Получение данных по ID студента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные успешно получены"),
            @ApiResponse(responseCode = "404", description = "Студент не найден")
    })
    @GetMapping("/{id}")
    ResponseEntity<RsStudentDTO> findById(@PathVariable(name = "id")int id);

}
