package com.td.student_api.validator;


import com.td.student_api.entity.Student;
import com.td.student_api.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentValidator {

    public void validate(List<Student> students) {
        for(Student student : students){
            if( student.getReference() == null || student.getReference().isBlank() ){
                throw new BadRequestException("students.referance connot be null");
            }
            if(student.getFirstName() == null || student.getFirstName().isBlank() ){
                throw new BadRequestException("students.firstName connot be null");
            }
            if(student.getLastName() == null || student.getLastName().isBlank() ){
                throw new BadRequestException("students.lastName connot be null");
            }

        }
    }
}
