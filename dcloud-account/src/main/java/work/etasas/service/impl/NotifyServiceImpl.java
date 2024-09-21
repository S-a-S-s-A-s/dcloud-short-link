package work.etasas.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import work.etasas.service.NotifyService;

import java.util.concurrent.TimeUnit;

/**
 * @author sas
 * @create 2024-09-15-9:14
 */
@Service
@Slf4j
public class NotifyServiceImpl implements NotifyService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Async("accountThreadPool")
    public void testSend() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("send sms success");
//        ResponseEntity<String> response = restTemplate.getForEntity("http://old.xdclass.net", String.class);
//        String body = response.getBody();
//        log.info("body: {}", body);

    }
}
