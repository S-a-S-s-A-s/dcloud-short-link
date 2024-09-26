package work.etasas.service.impl;

import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import work.etasas.component.SmsComponent;
import work.etasas.config.SmsConfig;
import work.etasas.constant.RedisKey;
import work.etasas.enums.BizCodeEnum;
import work.etasas.enums.SendCodeEnum;
import work.etasas.service.NotifyService;
import work.etasas.util.CheckUtil;
import work.etasas.util.CommonUtil;
import work.etasas.util.JsonData;

/**
 * @author sas
 * @create 2024-09-15-9:14
 */
@Service
@Slf4j
public class NotifyServiceImpl implements NotifyService {

    private static final int  CODE_EXPIRED = 60 * 5 * 1000;

    @Autowired
    private SmsComponent smsComponent;

    @Autowired
    private SmsConfig smsConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public JsonData sendCode(SendCodeEnum sendCodeEnum, String to) {

        String cacheKey = String.format(RedisKey.CHECK_CODE_KEY, sendCodeEnum.name(), to);

        String cacheValue = redisTemplate.opsForValue().get(cacheKey);
        //不为空并且在有效期内
        if(StringUtil.isNotBlank(cacheValue)) {
            long ttl = Long.parseLong(cacheValue.split("_")[1]);
            long leftTime = CommonUtil.getCurrentTimestamp() - ttl;
            if(leftTime < 60 * 1000) {
                log.info("【短信验证码】发送过于频繁");
                return JsonData.buildResult(BizCodeEnum.CODE_LIMITED);
            }
        }

        String code = CommonUtil.getRandomCode(6);

        String value = code + "_" + CommonUtil.getCurrentTimestamp();


        //param "**code**:12345,**minute**:5"
        String param = "**code**:" + code + ",**minute**:5";
        if (CheckUtil.isEmail(to)) {
            //发送邮件 TODO
        }else if (CheckUtil.isPhone(to)) {
            //发送短信
            smsComponent.send(to, smsConfig.getTemplateId(), param);
        }else{
            return JsonData.buildResult(BizCodeEnum.CODE_TO_ERROR);
        }
        redisTemplate.opsForValue().set(cacheKey, value, CODE_EXPIRED, java.util.concurrent.TimeUnit.MILLISECONDS);
        return JsonData.buildSuccess();
    }
}
