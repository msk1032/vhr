package org.studyhub.vhr.controller.system.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.studyhub.vhr.model.RespBean;
import org.studyhub.vhr.service.DepartmentService;

/**
 * @author haoren
 * @create 2023-02-28 10:00
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public RespBean getAllDepartments() {
        return RespBean.ok("", departmentService.getAllDepartments());
    }
}
