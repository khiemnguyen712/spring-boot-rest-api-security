package com.suka.springboot.superstudent.dao;

import com.suka.springboot.superstudent.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

// Add basic CRUD operation to the repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
