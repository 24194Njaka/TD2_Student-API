package com.td2.student_api.server;


import com.td2.student_api.entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServer {
    private final List<Student> students = new ArrayList<>();

    public List<Student> addStudents(List<Student> newStudents) {
        students.addAll(newStudents);
        return students;

    }

    public List<Student> getStudents() {
        return students;
    }
}
