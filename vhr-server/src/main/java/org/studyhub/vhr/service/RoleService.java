package org.studyhub.vhr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studyhub.vhr.mapper.MenuMapper;
import org.studyhub.vhr.mapper.RoleMapper;
import org.studyhub.vhr.model.Role;

import java.util.List;

/**
 * @author haoren
 * @create 2023-02-26 14:45
 */
@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    MenuMapper menuMapper;
    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    public Integer addRole(Role role) {

        if(!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_"+role.getName());
        }
        return roleMapper.insert(role);
    }
}
