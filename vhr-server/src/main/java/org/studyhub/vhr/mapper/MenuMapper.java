package org.studyhub.vhr.mapper;

import org.studyhub.vhr.model.Menu;

import java.util.List;


public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getMenuByHrId(Integer id);

    List<Menu> getAllMenusWithRole();

    List<Menu> getMenus();

    List<String> getAllPath(Integer id);

    List<Menu> getAllMenus();

    List<Menu> bulidMenuTree();

    List<Integer> getMidsByRoleId(Integer rid);


}