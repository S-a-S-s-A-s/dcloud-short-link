package work.etasas.manager;

import work.etasas.model.LinkGroupDO;

import java.util.List;

/**
 * @author sas
 * @create 2024-10-29-14:33
 */
public interface LinkGroupManager {
    int add(LinkGroupDO linkGroupDO);

    int del(Long groupId, long accountNo);

    LinkGroupDO detail(Long groupId, long accountNo);

    List<LinkGroupDO> list(long accountNo);

    int update(LinkGroupDO linkGroupDO);
}
