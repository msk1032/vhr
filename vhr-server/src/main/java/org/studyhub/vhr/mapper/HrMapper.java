package org.studyhub.vhr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.studyhub.vhr.model.Hr;
import org.studyhub.vhr.model.Role;
import java.util.List;

@Mapper
public interface HrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hr record);

    int insertSelective(Hr record);

    Hr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hr record);

    int updateByPrimaryKey(Hr record);

    Hr loadUserByUsername(String username);

    List<Role> getHrRolesById(Integer id);

}