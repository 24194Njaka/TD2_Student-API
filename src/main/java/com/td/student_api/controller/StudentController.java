package com.td.student_api.controller;

import com.td.student_api.entity.Student;
import com.td.student_api.server.StudentServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


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

    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@RequestBody List<Student> newStudents) {
        try{
            List<Student> result = studentServer.addStudents(newStudents);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);

        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("erruer servuer : "  + e.getMessage());

        }
    }

    @GetMapping(value = "/students" , produces = {"text/plain" , "application/json"} )
    public ResponseEntity<?> getStudents(@RequestHeader(value = "Accept" , required = false) String accept) {
        try{
            if(accept == null || accept.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("header manquant");
            }
            if(accept.equals("text/plain")) {
                String names = studentServer.getStudents()
                        .stream()
                        .map(student -> student.getFirstName() + " " + student.getLastName())
                        .collect(Collectors.joining(", "));
                return ResponseEntity.ok()
                        .header("content-type", "text/plain")
                        .body(names);
            } else if (accept.equals("application/json")) {
                return ResponseEntity.ok(studentServer.getStudents());
                
            } else {
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                        .body("format no supporté");
            }

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("erruer servuer : "  + e.getMessage());

        }

    }
}
