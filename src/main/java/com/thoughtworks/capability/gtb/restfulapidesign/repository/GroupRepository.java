package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class GroupRepository {

    private static final Integer GROUP_NUM = 6;

    private static final String GROUP_NAME = "组";

    private static final String GROUP_NOTE = "暂无备注";

    private final StudentRepository studentRepository;

    public GroupRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private Map<Integer, Group> groups = new HashMap<>();

    public List<Group> findAll() {
        List<Group> groupsList = new ArrayList<>();
        for (Map.Entry<Integer, Group> group: groups.entrySet()) {
            groupsList.add(group.getValue());
        }
        return groupsList;
    }

    public Optional<Group> findById(Integer groupId) {
        return Optional.ofNullable(groups.get(groupId - 1));
    }

    public void updateById(Integer groupId, Group groupRequest) {
        groups.get(groupId).setName(groupRequest.getName());
    }

    public void save(Group group) {
        groups.put(group.getId(), group);
    }

    public void deleteAll() {
        groups.clear();
    }

    public void grouping() {
        groups.clear();
        List<Student> shuffleStudents = new ArrayList<>(studentRepository.findAll());
        Collections.shuffle(shuffleStudents);
        int groupIndex = 0;
        for (Student student: shuffleStudents) {
            List<Student> students = new ArrayList<>();
            Group group = new Group(groupIndex, groupIndex + GROUP_NAME, GROUP_NOTE, students);
            if (groups.size() < GROUP_NUM) {
                groups.put(groupIndex, group);
            }
            groups.get(groupIndex).setId(groupIndex + 1);
            groups.get(groupIndex).setName(groupIndex + 1 + GROUP_NAME);
            groups.get(groupIndex).getStudentList().add(student);
            groupIndex++;
            if (groupIndex == GROUP_NUM)
                groupIndex = 0;
        }
    }
}
