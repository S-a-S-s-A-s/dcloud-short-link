package work.etasas.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author sas
 * @create 2024-10-25-14:55
 */
@Configuration
@Slf4j
public class SnowFlakeWorkIdConfig {

    static {
        try {
            InetAddress inetAddress = Inet4Address.getLocalHost();

            String hostAddressIp = inetAddress.getHostAddress();

            String workId = Math.abs(hostAddressIp.hashCode()) % 1024 + "";

            System.setProperty("workId", workId);

            log.info("workId:{}", workId);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
