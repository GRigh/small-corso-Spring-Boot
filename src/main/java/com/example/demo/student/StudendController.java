package com.example.demo.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudendController {
    @GetMapping
    public List<Student> getStudents() {
        return
                List.of(
                        new Student(
                                1L,
                                "Mariam",
                                LocalDate.now(),
                                21,
                                "Mariam.info@gmail.com"
                        )
                );
    }
}
