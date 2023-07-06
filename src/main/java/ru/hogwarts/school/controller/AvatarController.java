package ru.hogwarts.school.controller;

import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.StudentDtoOut;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.io.IOException;

@RestController
@RequestMapping("/avatars")
public class AvatarController {
    private final AvatarService avatarService;
    private final StudentService studentService;

    public AvatarController(AvatarService avatarService, StudentService studentService) {
        this.avatarService = avatarService;
        this.studentService = studentService;
    }

    @GetMapping("/{id}/from-db")
    public ResponseEntity<byte[]> getFromDb(@PathVariable long id) {
        return build(avatarService.getFromDb(id));
    }

    @GetMapping("/{id}/from-fs")
    public ResponseEntity<byte[]> getFromFs(@PathVariable long id) {
        return build(avatarService.getFromFs(id));
    }

    private ResponseEntity<byte[]> build(Pair<byte[], String> pair) {
        byte[] data = pair.getFirst();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(pair.getSecond()))
                .contentLength(data.length)
                .body(data);
    }

    @PostMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadAvatar(@PathVariable long id, @RequestParam MultipartFile avatar) throws IOException {
        Student student = studentService.getById(id);
        avatarService.create(student, avatar);
        return ResponseEntity.ok().build();
    }
}
