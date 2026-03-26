package com.td.student_api.controller;

import com.td.student_api.entity.Student;
import com.td.student_api.server.StudentServer;
import com.td.student_api.validator.StudentValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController

@AllArgsConstructor

public class welcomeController {
    private final StudentServer studentServer;
    private final StudentValidator  studentValidator;



    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(required = false) String name ) {
        if(name == null || name.isBlank()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Paramètre manquant");
        } else  {
            return ResponseEntity.ok("Welcome " + name);

        }

    }
}
