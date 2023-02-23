package org.studyhub.vhr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.studyhub.vhr.mapper.HrMapper;
import org.studyhub.vhr.mapper.MenuMapper;
import org.studyhub.vhr.model.Hr;
import org.studyhub.vhr.model.Menu;
import org.studyhub.vhr.service.HrService;
import org.studyhub.vhr.service.MenuService;

import java.util.List;

@SpringBootTest
class VhrServerApplicationTests {
    @Autowired
    HrMapper hrMapper;
    @Autowired
    MenuService menuService;
    @Autowired
    HrService hrService;

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
