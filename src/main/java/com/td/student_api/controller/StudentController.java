package com.td.student_api.controller;

import com.td.student_api.server.StudentServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {
    private final StudentServer studentServer;


    public StudentController(StudentServer studentServer) {
        this.studentServer = studentServer;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(required = false) String name ) {
        if(name == null || name.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Paramètre manquant");
        } else  {
            return ResponseEntity.ok("Welcome " + name);

        }

    }
}
