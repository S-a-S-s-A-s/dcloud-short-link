package work.etasas.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.etasas.interceptor.LoginInterceptor;
import work.etasas.manager.DomainManager;
import work.etasas.model.DomainDO;
import work.etasas.service.DomainService;
import work.etasas.vo.DomainVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sas
 * @create 2024-11-06-0:22
 */
@Service
@Slf4j
public class DomainServiceImpl implements DomainService {

    @Autowired
    private DomainManager domainManager;

    @Override
    public List<DomainVO> listAll() {
        Long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();

        List<DomainDO> customDomainDOList = domainManager.listCustomDomain(accountNo);
        List<DomainDO> officialDomainDOList = domainManager.listOfficialDomain();
        //customDomainDOList.addAll(officialDomainDOList);
        officialDomainDOList.addAll(customDomainDOList);

        return officialDomainDOList.stream().map(obj -> beanProcess(obj)).collect(Collectors.toList());
    }

    private DomainVO beanProcess(DomainDO domainDO) {
        DomainVO domainVO = new DomainVO();
        BeanUtils.copyProperties(domainDO, domainVO);
        return domainVO;
    }

}
