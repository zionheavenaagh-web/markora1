package com.markora.markora;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Roll number is required")
    private String rollNumber;

    @NotBlank(message = "Student name is required")
    private String name;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer tamil;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer english;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer maths;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer science;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer social;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTamil() {
        return tamil;
    }

    public void setTamil(Integer tamil) {
        this.tamil = tamil;
    }

    public Integer getEnglish() {
        return english;
    }

    public void setEnglish(Integer english) {
        this.english = english;
    }

    public Integer getMaths() {
        return maths;
    }

    public void setMaths(Integer maths) {
        this.maths = maths;
    }

    public Integer getScience() {
        return science;
    }

    public void setScience(Integer science) {
        this.science = science;
    }

    public Integer getSocial() {
        return social;
    }

    public void setSocial(Integer social) {
        this.social = social;
    }

    public int getTotal() {
        return tamil + english + maths + science + social;
    }

    public double getPercentage() {
        return getTotal() / 5.0;
    }

    public boolean isPassed() {
        return tamil >= 35
            && english >= 35
            && maths >= 35
            && science >= 35
            && social >= 35;
    }

    public String getGrade() {
        if (!isPassed()) return "F";

        double percentage = getPercentage();

        if (percentage >= 90) return "O";
        if (percentage >= 80) return "A+";
        if (percentage >= 70) return "A";
        if (percentage >= 60) return "B+";
        if (percentage >= 50) return "B";

        return "C";
    }
}

