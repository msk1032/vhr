package org.studyhub.vhr.controller.system.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.studyhub.vhr.model.Hr;
import org.studyhub.vhr.model.RespBean;
import org.studyhub.vhr.service.HrService;

import java.util.List;

/**
 * @author haoren
 * @create 2023-03-08 16:15
 */
@RestController
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    HrService hrService;

    @GetMapping("/")
    public RespBean getAllHrs(){
        List<Hr> hrs = hrService.getAllHrs();
        return RespBean.ok("", hrs);
    }



}
