package work.etasas.controller.request;

import lombok.Data;

/**
 * @author sas
 * @create 2024-10-20-23:46
 */

@Data
public class AccountRegisterRequest {
    /**
     * 头像
     */
    private String headImg;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 盐，用于个人敏感信息处理
     */
    private String secret;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 用户名
     */
    private String username;

    /**
     * 短信验证码
     */
    private String code;
}
