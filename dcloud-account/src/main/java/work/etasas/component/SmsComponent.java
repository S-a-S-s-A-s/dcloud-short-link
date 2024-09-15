package work.etasas.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import work.etasas.config.SmsConfig;
import work.etasas.util.CommonUtil;

/**
 * @Title: SmsComponent
 * @Author sas
 * @Package work.etasas.component
 * @Date 2024/9/9 14:24
 */
@Component
@Slf4j
public class SmsComponent {

    private static final String urlTemplate = "https://gyytz.market.alicloudapi.com/sms/smsSend?mobile=%s&templateId=%s&smsSignId=%s&param=%s";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SmsConfig smsConfig;

    public void send(String to, String templateId, String param) {
        long beginTime = CommonUtil.getCurrentTimestamp();

        String url = String.format(urlTemplate, to, templateId, smsConfig.getSmsSignId(), param);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "APPCODE " + smsConfig.getAppCode());
        HttpEntity entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        long endTime = CommonUtil.getCurrentTimestamp();

        log.info("耗时：{}, url: {}, response: {}", endTime-beginTime,url, response);
        if(response.getStatusCode().is2xxSuccessful()) {
            log.info("send sms success");
        } else {
            log.error("send sms failed");
        }


    }


}
