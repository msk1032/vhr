package org.studyhub.vhr.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.studyhub.vhr.mapper.MenuMapper;
import org.studyhub.vhr.model.Hr;
import org.studyhub.vhr.model.Menu;

import java.util.List;

/**
 * @author haoren
 * @create 2023-02-23 14:10
 */
@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getMenuByHrId() {

        return menuMapper.getMenuByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());

    }

    public List<Menu> getAllMenusWithRole() {


        return menuMapper.getAllMenusWithRole();
    }
}
