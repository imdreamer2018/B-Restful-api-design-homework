package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {

    private Map<Integer, Student> studentMap = new HashMap<>();

    private Integer maxStudentId = 1;

    public Integer getMaxStudentId() {
        return maxStudentId;
    }

    public void setMaxStudentId(Integer maxStudentId) {
        this.maxStudentId = maxStudentId;
    }

    public void deleteAll() {
        studentMap.clear();
    }

    public void save(Student student) {
        studentMap.put(student.getId(), student);
    }

    public Optional<Student> findById(Integer studentId) {
        return Optional.ofNullable(studentMap.get(studentId));
    }

    public Optional<Student> findByName(String name) {
        for (Map.Entry<Integer, Student> student: studentMap.entrySet()) {
            if (student.getValue().getName().equals(name))
                return Optional.of(student.getValue());
        }
        return Optional.empty();
    }

    public void deleteById(Integer studentId) {
        studentMap.remove(studentId);
    }

    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        for (Map.Entry<Integer, Student> student: studentMap.entrySet()) {
            students.add(student.getValue());
        }
        return students;
    }

    public void updateById(Integer studentId, Student studentRequest) {
        studentMap.remove(studentId);
        studentMap.put(studentId, studentRequest);
    }
}
