package org.studyhub.vhr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.studyhub.vhr.mapper.HrMapper;
import org.studyhub.vhr.model.Hr;

@SpringBootTest
class VhrServerApplicationTests {
    @Autowired
    HrMapper hrMapper;

    @Test
    void contextLoads() {
        Hr hr = hrMapper.loadUserByUsername("libai");
        System.out.println(hr);
    }

}
