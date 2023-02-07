package com.registationSystem.regSys.rest;

import com.registationSystem.regSys.dto.rq.RqCoachDTO;
import com.registationSystem.regSys.dto.rs.RsCoachDTO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
import com.registationSystem.regSys.exception.FindException;
import com.registationSystem.regSys.exception.UpdateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/coaches")
public interface CoachApi {

    @Operation(summary = "Создание тренера")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Успешно создано"),
            @ApiResponse(responseCode = "400", description = "Данные не прошли валидацию")
    })
    @PostMapping
    ResponseEntity<?> create(@Validated @RequestBody RqCoachDTO rqCoachDTO);

    @Operation(summary = "Получение данных всех тренеров")
    @ApiResponse(responseCode = "201", description = "Данные успешно получены")
    @GetMapping()
    ResponseEntity<List<RsCoachDTO>> findAll();

    @Operation(summary = "Получение незакончченных занятий")
    @ApiResponse(responseCode = "201", description = "Данные успешно получены")
    @GetMapping("/{id}/unfinishedLessons")
    ResponseEntity<List<RsLessonDTO>> getUnfinishedLessons(@RequestParam(name = "id") int coachId) throws FindException;

    @Operation(summary = "Получение закончченных занятий")
    @ApiResponse(responseCode = "201", description = "Данные успешно получены")
    @GetMapping("/{id}/finishedLessons")
    ResponseEntity<List<RsLessonDTO>> getFinishedLessons(@RequestParam(name = "id") int coachId) throws FindException;

    @Operation(summary = "Обновление данных тренера")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно обновлён"),
            @ApiResponse(responseCode = "404", description = "Тренер не найден"),
            @ApiResponse(responseCode = "400", description = "Данные не прошли валидацию")
    })
    @PutMapping("/{id}")
    ResponseEntity<?> update(@Validated @RequestBody RqCoachDTO rqCoachDTO, @PathVariable(name = "id") int coachId) throws UpdateException;
}
