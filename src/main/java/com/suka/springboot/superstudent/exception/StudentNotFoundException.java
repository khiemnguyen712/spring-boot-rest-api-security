package com.suka.springboot.superstudent.exception;

public class StudentNotFoundException extends RuntimeException {

    // Return an error message
    public StudentNotFoundException(int studentId) {
        super("Student ID not found: " + studentId);
    }
}