package org.studyhub.vhr.controller.system.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.studyhub.vhr.model.Department;
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

    @PutMapping("/")
    public RespBean addDepartment(@RequestBody Department department){
        if(1 == departmentService.addDepartment(department)){
            return RespBean.ok("add success");
        }
        return RespBean.error("add fail");
    }
    @DeleteMapping("/{id}")
    public RespBean deleteDepartment(@PathVariable Integer id) {
        if(1 == departmentService.deleteDepartment(id)){
            return RespBean.ok("delete success");
        }
        return RespBean.error("delete fail");
    }
}
