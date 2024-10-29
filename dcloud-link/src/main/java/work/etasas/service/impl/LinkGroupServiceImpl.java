package work.etasas.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.etasas.controller.request.LinkGroupAddRequest;
import work.etasas.controller.request.LinkGroupUpdateRequest;
import work.etasas.interceptor.LoginInterceptor;
import work.etasas.manager.LinkGroupManager;
import work.etasas.model.LinkGroupDO;
import work.etasas.service.LinkGroupService;
import work.etasas.vo.LinkGroupVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 二当家小D
 * @since 2024-10-29
 */
@Service
@Slf4j
public class LinkGroupServiceImpl  implements LinkGroupService {

    @Autowired
    private LinkGroupManager linkGroupManager;

    @Override
    public int add(LinkGroupAddRequest addRequest) {

        long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();

        LinkGroupDO linkGroupDO = new LinkGroupDO();
        linkGroupDO.setTitle(addRequest.getTitle());
        linkGroupDO.setAccountNo(accountNo);

        int rows = linkGroupManager.add(linkGroupDO);

        return rows;
    }

    @Override
    public int del(Long groupId) {
        //防止删除别人的分组
        long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();
        return linkGroupManager.del(groupId,accountNo);
    }

    @Override
    public LinkGroupVO detail(Long groupId) {

        long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();

        LinkGroupDO linkGroupDO = linkGroupManager.detail(groupId,accountNo);

        if(linkGroupDO == null) {
            return null;
        }

        LinkGroupVO linkGroupVO = new LinkGroupVO();
        BeanUtils.copyProperties(linkGroupDO,linkGroupVO);

        return linkGroupVO;
    }

    @Override
    public List<LinkGroupVO> list() {
        long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();

        List<LinkGroupDO> linkGroupDOList = linkGroupManager.list(accountNo);

        if(linkGroupDOList != null && !linkGroupDOList.isEmpty()) {
            return linkGroupDOList.stream().map(obj -> {

                LinkGroupVO linkGroupVO = new LinkGroupVO();
                BeanUtils.copyProperties(obj,linkGroupVO);
                return linkGroupVO;

            }).collect(Collectors.toList());
        }


        return null;
    }

    @Override
    public int update(LinkGroupUpdateRequest request) {
        long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();
        LinkGroupDO linkGroupDO = new LinkGroupDO();
        linkGroupDO.setId(request.getId());
        linkGroupDO.setTitle(request.getTitle());
        linkGroupDO.setAccountNo(accountNo);

        int rows = linkGroupManager.update(linkGroupDO);
        return rows;
    }
}
