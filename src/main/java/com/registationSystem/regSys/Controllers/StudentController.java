package com.registationSystem.regSys.Controllers;

import com.registationSystem.regSys.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @GetMapping("/student")
    public Student student() {
        return new Student("Billy", "Herrington", 40);
    }
}
