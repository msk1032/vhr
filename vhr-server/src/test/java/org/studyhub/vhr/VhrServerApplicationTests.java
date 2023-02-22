package org.studyhub.vhr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.studyhub.vhr.mapper.HrMapper;
import org.studyhub.vhr.model.Hr;
import org.studyhub.vhr.service.HrService;

@SpringBootTest
class VhrServerApplicationTests {
    @Autowired
    HrMapper hrMapper;

    @Autowired
    HrService hrService;

    @Test
    void contextLoads() {

        Hr user = (Hr) hrService.loadUserByUsername("libai");

        System.out.println(user);

        //Hr hr = hrMapper.loadUserByUsername("libai");
        //System.out.println(hr);
    }

}
