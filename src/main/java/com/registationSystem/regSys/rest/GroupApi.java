package com.registationSystem.regSys.rest;

import com.registationSystem.regSys.dto.rq.RqGroupDTO;
import com.registationSystem.regSys.dto.rs.RsGroupDTO;
import com.registationSystem.regSys.dto.rs.RsStudentDTO;
import com.registationSystem.regSys.exception.DeleteException;
import com.registationSystem.regSys.exception.FindException;
import com.registationSystem.regSys.exception.RegistrationException;
import com.registationSystem.regSys.exception.UpdateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/groups")
public interface GroupApi {
    @Operation(summary = "Создание группы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Успешно создана"),
            @ApiResponse(responseCode = "400", description = "Данные не прошли валидацию")
    })
    @PostMapping()
    ResponseEntity<?> create(@Validated @RequestBody RqGroupDTO rqGroupDTO);

    @Operation(summary = "Обновление данных студента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно обновлён"),
            @ApiResponse(responseCode = "400", description = "Данные не прошли валидацию, размер группы не подходит, группа полная"),
            @ApiResponse(responseCode = "404", description = "Группа не найдена")
    })
    @PutMapping("/{id}")
    ResponseEntity<?> update(@Validated @RequestBody RqGroupDTO rqGroupDTO, @PathVariable(name = "id") int id) throws UpdateException;

    @Operation(summary = "Получение данных всех групп")
    @ApiResponse(responseCode = "200", description = "Данные успешно получены")
    @GetMapping()
    ResponseEntity<List<RsGroupDTO>> findAll();

    @Operation(summary = "Получение данных группы по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные успешно получены"),
            @ApiResponse(responseCode = "404", description = "Группа не найдена")
    })
    @GetMapping("/{id}")
    ResponseEntity<RsGroupDTO> find(@PathVariable(name = "id") int id) throws FindException;

    @Operation(summary = "Запись студента в группу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные успешно получены"),
            @ApiResponse(responseCode = "404", description = "Студент не найден, группа не найдена")
    })
    @PostMapping("/{id}/{studentId}")
    ResponseEntity<?> register(@PathVariable(name = "id") int groupId, @PathVariable(name = "studentId") int studentId) throws FindException, RegistrationException;

    @Operation(summary = "Удаление всех студентов из группы по ID группы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Студенты успешно удалены"),
            @ApiResponse(responseCode = "404", description = "Группа не найдена")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable(name = "id") int id) throws DeleteException;

    @Operation(summary = "Получение списка студентов группы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные успешно получены"),
            @ApiResponse(responseCode = "404", description = "Группа не найдена")
    })
    @GetMapping("/{id}/students")
    ResponseEntity<List<RsStudentDTO>> getStudents(@PathVariable(name = "id") int groupId) throws FindException;
}
