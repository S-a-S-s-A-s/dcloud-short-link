package work.etasas.biz;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import work.etasas.LinkApplication;
import work.etasas.manager.DomainManager;
import work.etasas.model.DomainDO;

import java.util.List;

/**
 * @author sas
 * @create 2024-10-28-15:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LinkApplication.class)
@Slf4j
public class DomainTest {

    @Autowired
    private DomainManager domainManager;



    @Test
    public void testDomainList() {
        List<DomainDO> domainVOS = domainManager.listOfficialDomain();
        log.info("domainVOS:{}",domainVOS);
    }


}
