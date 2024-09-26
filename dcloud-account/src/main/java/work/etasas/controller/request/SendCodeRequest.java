package work.etasas.controller.request;

import lombok.Data;

/**
 * @author sas
 * @create 2024-09-26-14:49
 */
@Data
public class SendCodeRequest {
    private String captcha;

    private String to;
}
