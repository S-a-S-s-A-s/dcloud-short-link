package work.etasas.manager;

import work.etasas.enums.DomainTypeEnum;
import work.etasas.model.DomainDO;

import java.util.List;

/**
 * @author sas
 * @create 2024-11-06-0:07
 */
public interface DomainManager {

    DomainDO findById(Long id, Long accountNo);


    DomainDO findByDomainTypeAndId(Long id, DomainTypeEnum domainType);

    int add(DomainDO domain);


    List<DomainDO> listOfficialDomain();

    List<DomainDO> listCustomDomain(Long accountNo);
}
