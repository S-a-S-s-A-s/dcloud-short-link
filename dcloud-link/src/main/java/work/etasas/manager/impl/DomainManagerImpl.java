package work.etasas.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import work.etasas.enums.DomainTypeEnum;
import work.etasas.manager.DomainManager;
import work.etasas.mapper.DomainMapper;
import work.etasas.model.DomainDO;

import java.util.List;

/**
 * @author sas
 * @create 2024-11-06-0:07
 */
@Component
@Slf4j
public class DomainManagerImpl implements DomainManager {

    @Autowired
    private DomainMapper domainMapper;

    @Override
    public DomainDO findById(Long id, Long accountNo) {
        return domainMapper.selectOne(new QueryWrapper<DomainDO>()
                .eq("id", id)
                .eq("account_no", accountNo)
        );
    }

    @Override
    public DomainDO findByDomainTypeAndId(Long id, DomainTypeEnum domainType) {
        return domainMapper.selectOne(new QueryWrapper<DomainDO>()
                .eq("id", id)
                .eq("domain_type", domainType.name())
        );
    }

    @Override
    public int add(DomainDO domain) {
        return domainMapper.insert(domain);
    }

    @Override
    public List<DomainDO> listOfficialDomain() {
        return domainMapper.selectList(new QueryWrapper<DomainDO>()
                .eq("domain_type", DomainTypeEnum.OFFICIAL.name())
        );
    }

    @Override
    public List<DomainDO> listCustomDomain(Long accountNo) {
        return domainMapper.selectList(new QueryWrapper<DomainDO>()
                .eq("account_no", accountNo)
                .eq("domain_type", DomainTypeEnum.CUSTOM.name())
        );
    }
}
