package com.registationSystem.regSys.rest;

import com.registationSystem.regSys.dto.rq.RqCoachDTO;
import com.registationSystem.regSys.dto.rs.RsCoachDTO;
import com.registationSystem.regSys.dto.rs.RsLessonDTO;
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
    @ApiResponse(responseCode = "201", description = "Успешно создано")
    @PostMapping
    ResponseEntity<?> create(@Validated @RequestBody RqCoachDTO rqCoachDTO);

    @Operation(summary = "Получение данных всех тренеров")
    @ApiResponse(responseCode = "201", description = "Данные успешно получены")
    @GetMapping()
    ResponseEntity<List<RsCoachDTO>> readAll();

    @Operation(summary = "Получение незакончченных занятий")
    @ApiResponse(responseCode = "201", description = "Данные успешно получены")
    @GetMapping("/{id}/unfinishedLessons")
    ResponseEntity<List<RsLessonDTO>> getUnfinishedLessons(@RequestParam(name = "id") int coachId);

    @Operation(summary = "Получение закончченных занятий")
    @ApiResponse(responseCode = "201", description = "Данные успешно получены")
    @GetMapping("/{id}/finishedLessons")
    ResponseEntity<List<RsLessonDTO>> getFinishedLessons(@RequestParam(name = "id") int coachId);
}
