package biz;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import work.etasas.LinkApplication;
import work.etasas.component.ShortLinkComponent;
import work.etasas.util.CommonUtil;

/**
 * @author sas
 * @create 2024-10-28-15:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LinkApplication.class)
@Slf4j
public class ShortLinkTest {

    @Autowired
    private ShortLinkComponent shortLinkComponent;

    @Test
    public void testMurmurHash() {
        for (int i = 0; i < 10; i++) {
            String url = "https://www.baidu.com?id=" + CommonUtil.generateUUID();

            long murmur3_32 = Hashing.murmur3_32().hashUnencodedChars(url).padToLong();
            log.info("url:{} murmur3_32:{}", url, murmur3_32);

        }
    }

    @Test
    public void testGenerateShortLink() {
        for (int i = 0; i < 10; i++) {
            String url = "https://www.baidu.com?id=" + CommonUtil.generateUUID();
            String shortLink = shortLinkComponent.generateShortLink(url);
            log.info("url:{} shortLink:{}", url, shortLink);
        }
    }

}
