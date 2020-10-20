package com.thoughtworks.capability.gtb.restfulapidesign.serviceTests;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
class StudentServiceTest {

    private StudentService studentService;

    @Mock
    StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    void setup() {
        initMocks(this);
        studentService = new StudentService(studentRepository);
        student = Student.builder()
                .id(1)
                .name("yangqian")
                .gender("male")
                .note("123")
                .build();
    }


    @Test
    void should_return_student_info_when_create_student_success() {
        Student newStudent = studentService.createStudent(student);
        assertEquals("yangqian", newStudent.getName());
        verify(studentRepository).save(student);
    }
}
