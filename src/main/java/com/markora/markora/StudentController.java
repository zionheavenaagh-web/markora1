package com.markora.markora;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    // Home page
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("students", repository.findAll());
        model.addAttribute("student", new Student());
        return "index";
    }

    // Add student
    @PostMapping("/save")
    public String saveStudent(
            @ModelAttribute Student student,
            RedirectAttributes redirectAttributes) {

        if (repository.existsByRollNumber(student.getRollNumber())) {
            redirectAttributes.addFlashAttribute(
                    "error", "Roll number already exists!");
            return "redirect:/";
        }

        repository.save(student);

        redirectAttributes.addFlashAttribute(
                "message", "Student saved successfully!");

        return "redirect:/";
    }

    // Search students
    @GetMapping("/search")
    public String search(
            @RequestParam(defaultValue = "") String keyword,
            Model model) {

        List<Student> students =
                repository
                .findByNameContainingIgnoreCaseOrRollNumberContainingIgnoreCase(
                        keyword, keyword);

        model.addAttribute("students", students);
        model.addAttribute("student", new Student());
        model.addAttribute("keyword", keyword);

        return "index";
    }

    // Open student details for editing
    @GetMapping("/edit")
    public String editStudent(
            @RequestParam Long id,
            Model model,
            RedirectAttributes redirectAttributes) {

        Student student = repository.findById(id).orElse(null);

        if (student == null) {
            redirectAttributes.addFlashAttribute(
                    "error", "Student not found!");
            return "redirect:/";
        }

        model.addAttribute("student", student);
        model.addAttribute("students", repository.findAll());

        return "index";
    }

    // Update student
    @PostMapping("/update")
    public String updateStudent(
            @ModelAttribute Student student,
            RedirectAttributes redirectAttributes) {

        if (student.getId() == null
                || !repository.existsById(student.getId())) {

            redirectAttributes.addFlashAttribute(
                    "error", "Student not found!");
            return "redirect:/";
        }

        if (repository.existsByRollNumberAndIdNot(
                student.getRollNumber(), student.getId())) {

            redirectAttributes.addFlashAttribute(
                    "error", "Roll number already exists!");
            return "redirect:/";
        }

        repository.save(student);

        redirectAttributes.addFlashAttribute(
                "message", "Student updated successfully!");

        return "redirect:/";
    }

    // Delete student
    @PostMapping("/delete")
    public String deleteStudent(
            @RequestParam Long id,
            RedirectAttributes redirectAttributes) {

        if (!repository.existsById(id)) {
            redirectAttributes.addFlashAttribute(
                    "error", "Student not found!");
            return "redirect:/";
        }

        repository.deleteById(id);

        redirectAttributes.addFlashAttribute(
                "message", "Student deleted successfully!");

        return "redirect:/";
    }
}