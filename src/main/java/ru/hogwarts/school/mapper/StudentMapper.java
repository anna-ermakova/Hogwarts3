package ru.hogwarts.school.mapper;
import org.springframework.stereotype.Component;
import ru.hogwarts.school.dto.StudentDtoIn;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.repository.FacultiesRepository;

import java.util.Optional;

@Component
public class StudentMapper {
    private final FacultyMapper facultyMapper;
    private final FacultiesRepository facultiesRepository;

    public StudentMapper(FacultyMapper facultyMapper, FacultiesRepository facultiesRepository) {
        this.facultyMapper = facultyMapper;
        this.facultiesRepository = facultiesRepository;
    }

    public StudentDtoOut toDto(Student student) {
        StudentDtoOut studentDtoOut = new StudentDtoOut();
        studentDtoOut.setId(student.getId());
        studentDtoOut.setName(student.getName());
        studentDtoOut.setAge(student.getAge());
        Optional.ofNullable(student.getFaculty())
                .ifPresent(faculty -> studentDtoOut.setFaculty(facultyMapper.toDto(faculty)));
        return studentDtoOut;
    }

    public Student toEntity(StudentDtoIn studentDtoIn) {
        Student student = new Student();
        student.setAge(studentDtoIn.getAge());
        student.setName(studentDtoIn.getName());
        Optional.ofNullable(studentDtoIn.getFacultyId())
                .ifPresent(facultyId -> facultiesRepository.findById(facultyId)
                        .orElseThrow(() -> new FacultyNotFoundException(facultyId)));
        return student;
    }
}