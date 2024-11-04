package work.etasas.manager;

import work.etasas.enums.ShortLinkStateEnum;
import work.etasas.model.GroupCodeMappingDO;

import java.util.Map;

/**
 * @author sas
 * @create 2024-11-04-14:40
 */
public interface GroupCodeMappingManager {

    GroupCodeMappingDO findByGroupIdAndMappingId(Long mappingId,Long accountNo,Long groupId);

    int add(GroupCodeMappingDO groupCodeMappingDO);

    int del(String shortLinkCode, Long accountNo, Long groupId);

    Map<String,Object> pageShortLinkByGroupId(Integer page, Integer size, Long accountNo,Long groupId);

    int updateGroupCodeMappingState(Long accountNo, Long groupId, Long mappingId, String shortLinkCode, ShortLinkStateEnum stateEnum);

}
