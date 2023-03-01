package org.studyhub.vhr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studyhub.vhr.mapper.DepartmentMapper;
import org.studyhub.vhr.model.Department;

import java.util.List;

/**
 * @author haoren
 * @create 2023-02-28 10:03
 */
@Service
public class DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getAllDepartments() {

        return departmentMapper.getAllDepartments(-1);
    }

    public Integer addDepartment(Department department) {
        department.setEnabled(true);
        return departmentMapper.insertSelective(department);
    }

    public Integer deleteDepartment(Integer id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }
}
