package com.example.demo.service;

import com.example.demo.exception.EmailDuplicationException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) throws EmailDuplicationException {

        if (studentRepository.findAllByEmail(student.getEmail()).isEmpty()) {
            studentRepository.save(student);
        } else throw new EmailDuplicationException("The email address " + student.getEmail() + " is already taken.");
    }
}
