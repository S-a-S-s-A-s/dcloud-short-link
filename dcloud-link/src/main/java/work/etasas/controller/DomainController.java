package work.etasas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.etasas.service.DomainService;
import work.etasas.util.JsonData;
import work.etasas.vo.DomainVO;

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
@RequestMapping("/api/domain/v1")
public class DomainController {

    @Autowired
    private DomainService domainService;


    /**
     * 获取所有域名
     * @return
     */
    @GetMapping("/list")
    public JsonData listAll(){
        List<DomainVO> list = domainService.listAll();
        return JsonData.buildSuccess(list);
    }


}

