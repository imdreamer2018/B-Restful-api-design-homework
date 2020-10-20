package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Group> createGroup() {
        return groupService.createGroup();
    }

    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable Integer id, @RequestBody Group group) {
        return groupService.updateGroup(id, group);
    }

    @GetMapping
    public List<Group> getGroups() {
        return groupService.getGroups();
    }

    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable Integer id) {
        return groupService.getGroupById(id);
    }
}
