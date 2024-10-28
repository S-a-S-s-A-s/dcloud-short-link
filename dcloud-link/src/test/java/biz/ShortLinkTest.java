package biz;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import work.etasas.util.CommonUtil;

/**
 * @author sas
 * @create 2024-10-28-15:07
 */
@Slf4j
public class ShortLinkTest {

    @Test
    public void testMurmurHash() {
        for (int i = 0; i < 10; i++) {
            String url = "https://www.baidu.com?id=" + CommonUtil.generateUUID();

            long murmur3_32 = Hashing.murmur3_32().hashUnencodedChars(url).padToLong();
            log.info("url:{} murmur3_32:{}", url, murmur3_32);

        }
    }

}
