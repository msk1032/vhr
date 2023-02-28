package org.studyhub.vhr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.studyhub.vhr.mapper.MenuMapper;
import org.studyhub.vhr.mapper.MenuRoleMapper;
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
    @Autowired
    MenuRoleMapper menuRoleMapper;

    public List<Menu> getMenuByHrId() {

        return menuMapper.getMenuByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());

    }

    public List<Menu> getAllMenusWithRole() {


        return menuMapper.getAllMenusWithRole();
    }

    public List<Menu> getAllMenus() {

        return menuMapper.getAllMenus();
    }

    public List<Integer> getMidsbyRId(Integer rid) {
        return menuMapper.getMidsByRoleId(rid);
    }

    @Transactional
    public Boolean updateMenuRole(Integer id, List<Integer> list) {
        menuRoleMapper.deleteByRid(id);
        Integer res = menuRoleMapper.insertRecord(id, list);

        return res == list.size();
    }
}
