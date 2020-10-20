package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.BadRequestException;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        student.setId(studentRepository.getMaxStudentId());
        studentRepository.setMaxStudentId(student.getId() + 1);
        studentRepository.save(student);
        return student;
    }

    public void deleteStudent(Integer studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (!studentOptional.isPresent())
            throw new BadRequestException("student is not existed!");
        studentRepository.deleteById(studentId);
    }

    public List<Student> getStudents(String gender) {
        List<Student> students = studentRepository.findAll();
        if (gender != null)
            return students.stream()
                    .filter(student -> student.getGender().equals(gender))
                    .collect(Collectors.toList());
        return students;
    }
}
