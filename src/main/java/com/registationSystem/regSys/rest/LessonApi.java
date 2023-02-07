package com.registationSystem.regSys.rest;

import com.registationSystem.regSys.dto.rq.RqLessonDTO;
import com.registationSystem.regSys.exception.CreationException;
import com.registationSystem.regSys.exception.UpdateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/lessons")
public interface LessonApi {
    @Operation(summary = "Создание занятия")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Успешно создано"),
            @ApiResponse(responseCode = "400", description = "Данные не прошли валидацию, время не подходит"),
            @ApiResponse(responseCode = "404", description = "Группа не найдена, тренер не найден"),
    })
    @PostMapping
    ResponseEntity<?> create(@Validated @RequestBody RqLessonDTO rqLessonDTO) throws CreationException;

    @Operation(summary = "Обновление данных занятия")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно обновлено"),
            @ApiResponse(responseCode = "400", description = "Данные не прошли валидацию, время не подходит"),
            @ApiResponse(responseCode = "404", description = "Группа не найдена, тренер не найден"),
    })
    @PutMapping("/{id}")
    ResponseEntity<?> update(@Validated @RequestBody RqLessonDTO rqLessonDTO, @PathVariable("id")int lessonId) throws UpdateException;
}
