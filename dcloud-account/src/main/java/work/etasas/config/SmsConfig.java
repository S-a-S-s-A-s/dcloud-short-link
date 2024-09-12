package work.etasas.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: SmsConfig
 * @Author sas
 * @Package work.etasas.config
 * @Date 2024/9/9 23:53
 */
@ConfigurationProperties(prefix = "sms")
@Configuration
@Data
public class SmsConfig {
    private String templateId;
    private String appCode;
    private String smsSignId;
}
