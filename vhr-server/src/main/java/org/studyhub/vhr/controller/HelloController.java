package org.studyhub.vhr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.studyhub.vhr.model.RespBean;

/**
 * @author haoren
 * @create 2023-02-23 9:19
 */

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/login")
    public RespBean doLogin() {
        return RespBean.error("please login");
    }

    @GetMapping("/employee/basic/hello")
    public String hello1() {
        return "hello1";
    }
    @GetMapping("/employee/advanced/hello")
    public  String hello2() {

        return "hello2";
    }
}
