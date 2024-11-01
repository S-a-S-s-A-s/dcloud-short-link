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
import work.etasas.manager.ShortLinkManager;
import work.etasas.model.ShortLinkDO;
import work.etasas.util.CommonUtil;

import java.util.Random;

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

    @Autowired
    private ShortLinkManager shortLinkManager;

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

    @Test
    public void testSaveShortLink() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int num1 = random.nextInt(10);
            int num2 = random.nextInt(100000000);
            int num3 = random.nextInt(100000000);
            String originalUrl = num1 + "xdclass" + num2 + ".net" + num3;
            String shortLinkCode = shortLinkComponent.generateShortLink(originalUrl);

            ShortLinkDO shortLinkDO = new ShortLinkDO();
            shortLinkDO.setCode(shortLinkCode);
            shortLinkDO.setAccountNo(Long.valueOf(num3));
            shortLinkDO.setSign(CommonUtil.MD5(originalUrl));
            shortLinkDO.setDel(0);

            shortLinkManager.addShortLink(shortLinkDO);

        }


    }

}
