package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.FacultyDtoOut;
import ru.hogwarts.school.dto.StudentDtoIn;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
@Tag(name = "Студенты.", description = "CRUD-операции и другие эндпоинты для работы со студентами.")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @Operation(summary = "Добавление студента.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Студент добавлен"
            )
    })
    public StudentDtoOut create(@Valid @RequestBody StudentDtoIn studentDtoIn) {
        return studentService.create(studentDtoIn);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение студента по id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Студент изменен."
            )
    })
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public StudentDtoOut update(@PathVariable("id") long id, @Valid @RequestBody StudentDtoIn studentDtoIn) {
        return studentService.update(id, studentDtoIn);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение студента по id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Студент получен."
            )
    })
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public StudentDtoOut get(@PathVariable("id") Long id) {
        return studentService.get(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление студента по id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Студент удален."
            )})
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public StudentDtoOut delete(@PathVariable("id") Long id) {
        return studentService.delete(id);
    }

    @GetMapping("/age/{age}")
    @Operation(summary = "Получение студентов по возрасту.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Все студенты по возрасту получены."
            )
    })
    public List<StudentDtoOut> getStudentByAge(@PathVariable int age) {
        return studentService.getStudentByAge(age);
    }

    @GetMapping("/age/{filter}")
    @Operation(summary = "Фильтр студентов по возрасту от-до.")
    public List<StudentDtoOut> findByAgeBetween(@RequestParam int ageFrom, @RequestParam int ageTo) {
        return studentService.findByAgeBetween(ageFrom, ageTo);
    }


    @GetMapping("/{id}/faculty")
    @Operation(summary = "Получить факультет студента.")
    public FacultyDtoOut findFaculty(@PathVariable("id") Long id) {
        return studentService.findFaculty(id);
    }

    @PatchMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public StudentDtoOut uploadAvatar(@PathVariable Long id, @RequestPart("avatar") MultipartFile multipartFile) {
        return studentService.uploadAvatar(id, multipartFile);
    }

}
