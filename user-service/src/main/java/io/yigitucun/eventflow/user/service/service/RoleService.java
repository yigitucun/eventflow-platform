package io.yigitucun.eventflow.user.service.service;

import io.yigitucun.eventflow.user.service.dto.Role;
import io.yigitucun.eventflow.user.service.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleMapper roleMapper;

    public RoleService(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public List<Role> getAllRoles(){
        return roleMapper.findAll();
    }
}
