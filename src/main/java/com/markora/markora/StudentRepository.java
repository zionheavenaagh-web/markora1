package com.markora.markora;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    boolean existsByRollNumber(String rollNumber);

    boolean existsByRollNumberAndIdNot(
            String rollNumber, Long id);

    List<Student>
    findByNameContainingIgnoreCaseOrRollNumberContainingIgnoreCase(
            String name, String rollNumber);
}