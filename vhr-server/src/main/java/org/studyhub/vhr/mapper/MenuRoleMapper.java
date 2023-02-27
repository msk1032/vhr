package org.studyhub.vhr.mapper;


import org.apache.ibatis.annotations.Param;
import org.studyhub.vhr.model.MenuRole;

import java.util.List;

public interface MenuRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    MenuRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);

    void deleteByRid(@Param("rid") Integer rid);

    Integer insertRecord(@Param("rid") Integer rid,@Param("mids") List<Integer> mids);
}