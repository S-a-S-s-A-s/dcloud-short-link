package work.etasas.biz;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import work.etasas.AccountApplication;
import work.etasas.component.SmsComponent;
import work.etasas.config.SmsConfig;

/**
 * @Title: SmsTest
 * @Author sas
 * @Package work.etasas.biz
 * @Date 2024/9/10 9:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountApplication.class)
@Slf4j
public class SmsTest {

    @Autowired
    private SmsComponent smsComponent;

    @Autowired
    private SmsConfig smsConfig;

    @Test
    public void testSend() {
        smsComponent.send("17773144837", smsConfig.getTemplateId(), "**code**:12345,**minute**:5");
    }
}
