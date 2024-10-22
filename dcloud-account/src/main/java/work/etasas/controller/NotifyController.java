package work.etasas.controller;

import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.etasas.controller.request.SendCodeRequest;
import work.etasas.enums.BizCodeEnum;
import work.etasas.enums.SendCodeEnum;
import work.etasas.service.NotifyService;
import work.etasas.util.CommonUtil;
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
@RequestMapping("/api/notify/v1")
@Slf4j
public class NotifyController {

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final long CAPTCHA_EXPIRE_TIME = 1000 * 10 * 60;

    @GetMapping("captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        String text = captchaProducer.createText();
        log.info("验证码:{}", text);

        //存储Redis 配置过期时间
        redisTemplate.opsForValue().set(getCaptchaKey(request), text, CAPTCHA_EXPIRE_TIME, java.util.concurrent.TimeUnit.MILLISECONDS);


        BufferedImage bufferedImage = captchaProducer.createImage(text);

        try(ServletOutputStream outputStream = response.getOutputStream()) {
            ImageIO.write(bufferedImage, "jpg", outputStream);
            outputStream.flush();
        } catch (IOException e) {
            log.error("获取流出错", e);
        }


    }

    private String getCaptchaKey(HttpServletRequest request) {
        String ip = CommonUtil.getIpAddr(request);
        String userAgent = request.getHeader("User-Agent");

        String key = "account-service:captcha:" + CommonUtil.MD5(ip + userAgent);
        log.info("验证码key:{}", key);
        return key;
    }

    @RequestMapping("send_code")
    public JsonData sendCode(@RequestBody SendCodeRequest sendCodeRequest, HttpServletRequest request) {

        String key = getCaptchaKey(request);

        String cache_captcha = redisTemplate.opsForValue().get(key);
        String input_captcha = sendCodeRequest.getCaptcha();

        if (cache_captcha != null && cache_captcha.equalsIgnoreCase(input_captcha)) {
            //验证码通过
            redisTemplate.delete(key);
            JsonData jsonData = notifyService.sendCode(SendCodeEnum.USER_REGISTER, sendCodeRequest.getTo());
            return jsonData;
        }else{
            return JsonData.buildResult(BizCodeEnum.CODE_ERROR);
        }


    }
}
