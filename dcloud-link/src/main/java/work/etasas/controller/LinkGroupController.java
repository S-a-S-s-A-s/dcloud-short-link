package work.etasas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.etasas.controller.request.LinkGroupAddRequest;
import work.etasas.controller.request.LinkGroupUpdateRequest;
import work.etasas.enums.BizCodeEnum;
import work.etasas.service.LinkGroupService;
import work.etasas.util.JsonData;
import work.etasas.vo.LinkGroupVO;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 二当家小D
 * @since 2024-10-29
 */
@RestController
@RequestMapping("/api/group/v1")
public class LinkGroupController {


    @Autowired
    private LinkGroupService linkGroupService;

    /**
     * 添加分组
     * @param addRequest
     * @return
     */
    @PostMapping("/add")
    public JsonData add(@RequestBody LinkGroupAddRequest addRequest) {

        int rows = linkGroupService.add(addRequest);

        return rows==1?JsonData.buildSuccess():JsonData.buildResult(BizCodeEnum.GROUP_ADD_FAIL);
    }

    @DeleteMapping("/del/{groupId}")
    public  JsonData del(@PathVariable("groupId") Long groupId) {

        int rows = linkGroupService.del(groupId);
        return rows==1?JsonData.buildSuccess():JsonData.buildResult(BizCodeEnum.GROUP_NOT_EXIST);

    }

    @GetMapping("/detail/{groupId}")
    public JsonData detail(@PathVariable("groupId")Long groupId) {
        LinkGroupVO linkGroupVO = linkGroupService.detail(groupId);
        return JsonData.buildSuccess(linkGroupVO);
    }


    @GetMapping("list")
    public JsonData list() {
        List<LinkGroupVO> list= linkGroupService.list();
        return JsonData.buildSuccess(list);
    }


    @PutMapping("update")
    public JsonData update(@RequestBody LinkGroupUpdateRequest request) {
        int rows = linkGroupService.update(request);
        return rows==1?JsonData.buildSuccess():JsonData.buildResult(BizCodeEnum.GROUP_OPER_FAIL);

    }



}

