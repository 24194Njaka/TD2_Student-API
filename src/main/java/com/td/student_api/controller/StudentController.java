package com.td.student_api.controller;

import com.td.student_api.entity.Student;
import com.td.student_api.exception.BadRequestException;
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
public class StudentController {
    private final StudentServer studentServer;
    private final StudentValidator studentValidator;


    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@RequestBody List<Student> newStudents) {
        try{
            studentValidator.validate(newStudents);
            List<Student> result = studentServer.addStudents(newStudents);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(result);

        } catch (BadRequestException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
        catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("erruer servuer : "  + e.getMessage());

        }
    }

    @GetMapping(value = "/students" , produces = {"text/plain" , "application/json"} )
    public ResponseEntity<?> getStudents(@RequestHeader(value = "Accept" , required = false) String accept) {
        try{
            if(accept == null || accept.isBlank()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("header manquant");
            }
            if(accept.equals("text/plain")) {
                String names = studentServer.getStudents()
                        .stream()
                        .map(student -> student.getFirstName() + " " + student.getLastName())
                        .collect(Collectors.joining(", "));
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .header("content-type", "text/plain")
                        .body(names);
            } else if (accept.equals("application/json")) {
                return ResponseEntity.ok(studentServer.getStudents());
                
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_IMPLEMENTED)
                        .body("format no supporté");
            }

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("erruer servuer : "  + e.getMessage());

        }

    }
}
