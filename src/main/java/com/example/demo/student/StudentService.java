package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exits = studentRepository.existsById(studentId);
        if(!exits) {
            throw new IllegalStateException("student with id: " + studentId + " does not exists ");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId,String name, String email ) {
        boolean exits = studentRepository.existsById(studentId);
        if(!exits) {
            throw new IllegalStateException("student with id: " + studentId + " does not exists ");
        }
        Student student = studentRepository.getById(studentId);

        if(name != null && !name.isEmpty() && !Objects.equals(student.getName(), name) ) {
            student.setName(name);
        }

        if(email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email) ) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
            if(studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
