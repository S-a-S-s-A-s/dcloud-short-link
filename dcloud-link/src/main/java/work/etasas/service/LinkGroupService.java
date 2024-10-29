package work.etasas.service;

import work.etasas.controller.request.LinkGroupAddRequest;
import work.etasas.controller.request.LinkGroupUpdateRequest;
import work.etasas.vo.LinkGroupVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 二当家小D
 * @since 2024-10-29
 */
public interface LinkGroupService  {

    int add(LinkGroupAddRequest addRequest);

    int del(Long groupId);

    LinkGroupVO detail(Long groupId);

    List<LinkGroupVO> list();

    int update(LinkGroupUpdateRequest request);
}
