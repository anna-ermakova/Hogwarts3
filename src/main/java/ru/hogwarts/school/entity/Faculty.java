package ru.hogwarts.school.entity;
import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Обязательно для заполнения!")
    private String name;

    @NotBlank(message = "Обязательно для заполнения!")
    private String color;
    @OneToMany(mappedBy = "faculty")

    private List<Student> student;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getColor() {
        return color;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public Faculty(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
    public Faculty() {
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(id, faculty.id) && Objects.equals(name, faculty.name) && Objects.equals(color, faculty.color);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }
    @Override
    public String toString() {
        return "Faculty{" + "id=" + id + ", name='" + name + '\'' + ", color='" + color + '\'' + '}';
    }
}