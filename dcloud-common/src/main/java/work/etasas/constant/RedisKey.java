package work.etasas.constant;

/**
 * @author sas
 * @create 2024-09-26-15:28
 */
public class RedisKey {
    /**
     * 验证码缓存key，第一个是类型，第二个是唯一标识比如手机号或者邮箱
     */
    public static final String CHECK_CODE_KEY = "code:%s:%s";
}
