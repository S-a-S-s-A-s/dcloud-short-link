package work.etasas.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import work.etasas.enums.ShortLinkStateEnum;
import work.etasas.manager.GroupCodeMappingManager;
import work.etasas.mapper.GroupCodeMappingMapper;
import work.etasas.model.GroupCodeMappingDO;
import work.etasas.vo.GroupCodeMappingVO;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author sas
 * @create 2024-11-04-14:50
 */
@Component
@Slf4j
public class GroupCodeMappingManagerImpl implements GroupCodeMappingManager {

    @Autowired
    private GroupCodeMappingMapper groupCodeMappingMapper;

    @Override
    public GroupCodeMappingDO findByGroupIdAndMappingId(Long mappingId, Long accountNo, Long groupId) {

        GroupCodeMappingDO groupCodeMappingDO = groupCodeMappingMapper.selectOne(new QueryWrapper<GroupCodeMappingDO>()
                .eq("id", mappingId)
                .eq("group_id", groupId)
                .eq("account_no", accountNo)
        );
        return groupCodeMappingDO;
    }

    @Override
    public int add(GroupCodeMappingDO groupCodeMappingDO) {
        return groupCodeMappingMapper.insert(groupCodeMappingDO);
    }

    @Override
    public int del(String shortLinkCode, Long accountNo, Long groupId) {

        int rows = groupCodeMappingMapper.update(null, new UpdateWrapper<GroupCodeMappingDO>()
                .eq("code", shortLinkCode)
                .eq("account_no", accountNo)
                .eq("group_id", groupId).set("del", 1)
        );

        return rows;
    }

    @Override
    public Map<String, Object> pageShortLinkByGroupId(Integer page, Integer size, Long accountNo, Long groupId) {

        Page<GroupCodeMappingDO> pageInfo = new Page<>(page, size);
        Page<GroupCodeMappingDO> groupCodeMappingDOPage = groupCodeMappingMapper.selectPage(pageInfo, new QueryWrapper<GroupCodeMappingDO>()
                .eq("account_no", accountNo)
                .eq("group_id", groupId)
                .eq("del", 0)
        );

        Map<String, Object> pageMap = new HashMap<>(3);
        pageMap.put("total_record", groupCodeMappingDOPage.getTotal());
        pageMap.put("total_page", groupCodeMappingDOPage.getPages());
        pageMap.put("current_data", groupCodeMappingDOPage.getRecords()
                .stream().map(obj->beanProcess(obj)).collect(Collectors.toList()));

        return pageMap;
    }

    @Override
    public int updateGroupCodeMappingState(Long accountNo, Long groupId, Long mappingId, String shortLinkCode, ShortLinkStateEnum stateEnum) {
        int rows = groupCodeMappingMapper.update(null, new UpdateWrapper<GroupCodeMappingDO>()
                .eq("code", shortLinkCode)
                .eq("account_no", accountNo)
                .eq("group_id", groupId).set("state", stateEnum.name())
        );


        return rows;
    }

    private GroupCodeMappingVO beanProcess(GroupCodeMappingDO groupCodeMappingDO){
        GroupCodeMappingVO groupCodeMappingVO = new GroupCodeMappingVO();
        BeanUtils.copyProperties(groupCodeMappingDO, groupCodeMappingVO);
        return groupCodeMappingVO;
    }

}
