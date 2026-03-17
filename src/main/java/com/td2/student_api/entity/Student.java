package com.td2.student_api.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Student {
    private int reference;
    private String firstName;
    private String lastName;
    private int age;
}
