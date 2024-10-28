package work.etasas.component;

import org.springframework.stereotype.Component;
import work.etasas.util.CommonUtil;

/**
 * @author sas
 * @create 2024-10-28-15:20
 */
@Component
public class ShortLinkComponent {

    private static final String base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String generateShortLink(String url) {
        long murmur3_32 = CommonUtil.murmurHash32(url);
        //进制转化
        return toBase62(murmur3_32);
    }

    private String toBase62(long value) {
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            sb.append(base.charAt((int) (value % 62)));
            value /= 62;
        }
        return sb.reverse().toString();
    }


}
