package ru.hogwarts.school.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDtoIn;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.service.FacultyService;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/faculties")
@Tag(name = "Факультеты.", description = "CRUD-операции и другие эндпоинты для работы с факультетами.")
public class FacultyController {
    private final FacultyService facultyService;
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @PostMapping
    @Operation(summary = "Добавление факультета.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Факультет добавлен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Faculty.class)
                            )
                    }
            )
    })
    public FacultyDtoOut create(@Valid @RequestBody FacultyDtoIn facultyDtoIn) {
        return facultyService.create(facultyDtoIn);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Изменение факультета по id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Факультет изменен.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Faculty.class)
                            )
                    }
            )
    })
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public FacultyDtoOut update(@PathVariable("id") Long id, @Valid @RequestBody FacultyDtoIn facultyDtoIn) {
        return facultyService.update(id, facultyDtoIn);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Получение факультета по id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Факультет получен.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Faculty.class)
                            )
                    }
            )
    })
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public FacultyDtoOut getFaculty(@PathVariable("id") Long id) {
        return facultyService.getFacultyById(id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление факультета по id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Факультет удален."
            )})
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public FacultyDtoOut delete(@PathVariable("id") Long id) {
        return facultyService.delete(id);
    }
    @GetMapping("/color/{color}")
    @Operation(summary = "Получение факультетов по цвету.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все факультеты по цвету получены.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Faculty.class)
                            )
                    }
            )
    })
    public List<FacultyDtoOut> getAllFacultiesByColor(@PathVariable String color) {
        return facultyService.getAllFacultyByColor(color);
    }

    @GetMapping("/filter/{colorOrName}")
    @Operation(summary = "Фильтр факультетов по цвету или имени.")
    public List<FacultyDtoOut> findByColorOrName(@RequestParam String colorOrName) {
        return facultyService.findByColorOrName(colorOrName);
    }

    @GetMapping("/{id}/students")
    @Operation(summary = "Получить студентов факультета.")
    public List<StudentDtoOut> findStudents(@PathVariable("id") Long id) {
        return facultyService.findStudents(id);
    }
}