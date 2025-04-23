package com.suka.springboot.superstudent.controller;

import com.suka.springboot.superstudent.entity.Student;
import com.suka.springboot.superstudent.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    // Read all students (R)
    @GetMapping
    public List<Student> getAllStudent() {
        return studentService.findAllUser();
    }

    // Read a student by ID (R)
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.findById(id);
    }

    // Create a new student (C)
    @PostMapping
    public void addStudent(@RequestBody Student student) {
        studentService.save(student);
    }

    // Update an existing student (U)
    @PutMapping("/{id}")
    public void updateStudent(@PathVariable int id, @RequestBody Student student) {
        Student studentFound = studentService.findById(id);
    }

    // Delete a student by ID (D)
    @DeleteMapping("/{id}")
    public int deleteStudentById(@PathVariable int id) {

        studentService.delete(id);

        return id;
    }
}
