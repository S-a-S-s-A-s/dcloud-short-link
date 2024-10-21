package work.etasas.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import work.etasas.model.LoginUser;

import java.util.Date;

/**
 * @author sas
 * @create 2024-10-21-22:50
 */
@Slf4j
public class JWTUtil {

    /**
     * 主题
     */
    private static final String SUBJECT = "etasas";

    /**
     * 密钥
     */
    private static final String SECRET = "etasas.work";

    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX = "dcloud-link";

    /**
     * 过期时间
     */
    private static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;


    /**
     * 创建token
     * @param loginUser
     * @return
     */
    public static String createToken(LoginUser loginUser) {

        if(loginUser == null) {
            throw new NullPointerException("对象为空");
        }

        String token = Jwts.builder().setSubject(SUBJECT)
                // 配置payload
                .claim("accountNo", loginUser.getAccountNo())
                .claim("username", loginUser.getUsername())
                .claim("headImg", loginUser.getHeadImg())
                .claim("phone", loginUser.getPhone())
                .claim("mail", loginUser.getMail())
                .claim("auth", loginUser.getAuth())
                .setIssuedAt(new Date())
                .setExpiration(new Date(CommonUtil.getCurrentTimestamp() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        token = TOKEN_PREFIX + token;
        return token;
    }

    public static Claims parseToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            return claims;
        } catch (Exception e) {
            log.error("解析token失败", e);
            return null;
        }
    }
}
