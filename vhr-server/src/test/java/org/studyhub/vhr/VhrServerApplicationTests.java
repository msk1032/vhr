package org.studyhub.vhr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.studyhub.vhr.mapper.HrMapper;
import org.studyhub.vhr.mapper.MenuMapper;
import org.studyhub.vhr.mapper.RoleMapper;
import org.studyhub.vhr.model.Hr;
import org.studyhub.vhr.model.Menu;
import org.studyhub.vhr.model.Role;
import org.studyhub.vhr.service.HrService;
import org.studyhub.vhr.service.MenuService;
import org.studyhub.vhr.service.RoleService;

import java.util.List;

@SpringBootTest
class VhrServerApplicationTests {
    @Autowired
    HrMapper hrMapper;
    @Autowired
    MenuService menuService;

    @Autowired
    HrService hrService;

    @Autowired
    RoleService roleService;

    @Test
    void getAllHrs() {
        List<Hr> hrs = hrMapper.getAllHrs(3);
        System.out.println(hrs);
    }

    @Test
    void getMidsByRIde() {
        List<Integer> list = menuService.getMidsbyRId(1);

        System.out.println(list);
    }

    @Test
    void testMenuChildren() {
        List<Menu> menus = menuService.getAllMenus();
        for (Menu menu : menus) {
            System.out.println(menu);
        }
    }

    @Test
    void testRole() {
        Role role = new Role();
        role.setName("ROLE_1231");
        role.setNameZh("1231");
        if(role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_"+role.getName());
        }

        System.out.println(role.getName());
    }

    @Test
    void test01() {
        List<Menu> list = menuService.getMenuByHrId();
        System.out.println(list);
    }

    @Test
    void contextLoads() {

        Hr user = (Hr) hrService.loadUserByUsername("libai");

        System.out.println(user);

        //Hr hr = hrMapper.loadUserByUsername("libai");
        //System.out.println(hr);
    }

}
