package com.example.demo.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {
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
