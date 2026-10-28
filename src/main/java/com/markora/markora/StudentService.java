package com.markora.markora;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public int calculateTotal(Student student) {
        return student.getTotal();
    }

    public double calculatePercentage(Student student) {
        return student.getPercentage();
    }

    public String calculateResult(Student student) {
        return student.isPassed() ? "PASS" : "FAIL";
    }

    public String calculateGrade(Student student) {
        return student.getGrade();
    }
}

