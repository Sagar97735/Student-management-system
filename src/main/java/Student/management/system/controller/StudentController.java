package Student.management.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Student.management.system.entity.Student;
import Student.management.system.service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/students")
    public String listStudents(Model model) {

        model.addAttribute("students", service.getAllStudents());

        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {

        Student student = new Student();

        model.addAttribute("student", student);

        return "add_student";
    }

    @PostMapping("/students")
    public String saveStudent(Student student) {

        service.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {

        model.addAttribute("student", service.getStudentById(id));

        return "update_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model) {

        Student existingStudent = service.getStudentById(id);

        existingStudent.setId(id);
        existingStudent.setFirstname(student.getFirstname());
        existingStudent.setLastname(student.getLastname());
        existingStudent.setEmail(student.getEmail());

        service.updateStudent(existingStudent);

        return "redirect:/students";
    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {

        service.deleteStudentById(id);

        return "redirect:/students";
    }
}
