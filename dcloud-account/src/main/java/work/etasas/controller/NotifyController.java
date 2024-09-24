package work.etasas.controller;

import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.etasas.service.NotifyService;
import work.etasas.util.JsonData;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author sas
 * @create 2024-09-15-9:10
 */
@RestController
@RequestMapping("/api/account/v1")
@Slf4j
public class NotifyController {

    @Autowired
    private Producer kaptchaProducer;

    @Autowired
    private NotifyService notifyService;

    @GetMapping("kaptcha")
    public void getKaptcha(HttpServletRequest request, HttpServletResponse response) {
        String text = kaptchaProducer.createText();
        log.info("验证码:{}", text);

        //存储Redis 配置过期时间 TODO

        BufferedImage bufferedImage = kaptchaProducer.createImage(text);

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error("获取流出错", e);
        }


    }

    @RequestMapping("send_code")
    public JsonData sendCode() {
        return JsonData.buildSuccess();
    }
}
