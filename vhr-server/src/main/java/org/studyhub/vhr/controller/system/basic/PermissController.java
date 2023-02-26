package org.studyhub.vhr.controller.system.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.studyhub.vhr.model.RespBean;
import org.studyhub.vhr.model.Role;
import org.studyhub.vhr.service.MenuService;
import org.studyhub.vhr.service.RoleService;

import java.util.List;

/**
 * @author haoren
 * @create 2023-02-26 14:43
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    MenuService menuService;
    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public RespBean getAllRoles() {
        List<Role> roles =  roleService.getAllRoles();
        return RespBean.ok("", roles);
    }

    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role) {
        if(1 == roleService.addRole(role)) {
            return RespBean.ok("add role success");
        }
        return RespBean.ok("add role fail");
    }



}
