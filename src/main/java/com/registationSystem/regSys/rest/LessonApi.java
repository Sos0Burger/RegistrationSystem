package com.registationSystem.regSys.rest;

import com.registationSystem.regSys.dto.rq.RqLessonDTO;
import com.registationSystem.regSys.exception.CreationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/lessons")
public interface LessonApi {
    @Operation(summary = "Создание занятие")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Успешно создано"),
            @ApiResponse(responseCode = "404", description = "Группа не найдена"),
            @ApiResponse(responseCode = "404", description = "Тренер не найден"),
            @ApiResponse(responseCode = "400", description = "Время не подходит")
    })
    @PostMapping
    ResponseEntity<?> create(@Validated @RequestBody RqLessonDTO rqLessonDTO) throws CreationException;
}
