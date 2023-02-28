package org.studyhub.vhr.controller.config;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.studyhub.vhr.model.RespBean;
import org.studyhub.vhr.service.MenuService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author haoren
 * @create 2023-02-23 13:59
 */
@RestController
@RequestMapping("/system/config")
public class SystemConfigController {

    @Autowired
    Producer captchaProducer;
    @Autowired
    MenuService menuService;

    @GetMapping("menu")
    public RespBean getMenuById() {

        return RespBean.ok("success", menuService.getMenuByHrId());
    }
    @GetMapping("/kapchaImage")
    public RespBean getImage() throws IOException {

        String code = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(code);

        FastByteArrayOutputStream stream = new FastByteArrayOutputStream();
        ImageIO.write(image,"jpg",stream);
        byte[] bytes = stream.toByteArray();

        return RespBean.ok("success", Base64Utils.encodeToString(bytes));
    }

}
