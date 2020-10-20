package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.BadRequestException;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> createGroup() {
        groupRepository.grouping();
        return groupRepository.findAll();
    }

    public Group updateGroup(Integer groupId, Group groupRequest) {
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if (!groupOptional.isPresent()) {
            throw new BadRequestException("group is not existed!");
        }
        groupRepository.updateById(groupId, groupRequest);
        groupOptional.get().setName(groupRequest.getName());
        return groupOptional.get();
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(Integer groupId) {
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if (!groupOptional.isPresent()) {
            throw new BadRequestException("group is not existed!");
        }
        return groupOptional.get();
    }
}
