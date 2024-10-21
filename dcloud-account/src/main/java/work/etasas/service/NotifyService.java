package work.etasas.service;

import work.etasas.enums.SendCodeEnum;
import work.etasas.util.JsonData;

/**
 * @author sas
 * @create 2024-09-15-9:12
 */
public interface NotifyService {
    /**
     * 发送验证码
     * @param sendCodeEnum
     * @param to
     * @return
     */
    JsonData sendCode(SendCodeEnum sendCodeEnum, String to);

    /**
     * 检查验证码
     * @param sendCodeEnum
     * @param to
     * @param code
     * @return
     */
    boolean checkCode(SendCodeEnum sendCodeEnum, String to, String code);
}
