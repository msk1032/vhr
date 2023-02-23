package org.studyhub.vhr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.studyhub.vhr.model.RespBean;
import org.studyhub.vhr.service.MenuService;

/**
 * @author haoren
 * @create 2023-02-23 13:59
 */
@RestController
@RequestMapping("/system/config")
public class SystemConfigController {

    @Autowired
    MenuService menuService;

    @GetMapping("menu")
    public RespBean getMenuById() {

        return RespBean.ok("success", menuService.getMenuByHrId());
    }

}
