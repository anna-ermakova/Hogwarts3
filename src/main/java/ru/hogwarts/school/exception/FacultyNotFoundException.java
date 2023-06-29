package ru.hogwarts.school.exception;

public class FacultyNotFoundException extends RuntimeException {
    private final long id;


    public FacultyNotFoundException(long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "студент с id= " + id + " не найден!";
    }
}
