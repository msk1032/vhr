package org.studyhub.vhr.mapper;

import org.apache.ibatis.annotations.Param;
import org.studyhub.vhr.model.Department;
import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> getAllDepartments(Integer pid);

    void addDepartment(Department department);

    List<Department> getChildrenByParentId(Integer parentId);

    Integer deleteDepartmentByIds(@Param("list") Integer[] list);
}