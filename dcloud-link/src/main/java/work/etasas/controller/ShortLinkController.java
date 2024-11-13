package work.etasas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.etasas.controller.request.ShortLinkAddRequest;
import work.etasas.service.ShortLinkService;
import work.etasas.util.JsonData;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 二当家小D
 * @since 2024-10-29
 */
@RestController
@RequestMapping("/api/link/v1")
public class ShortLinkController {

    @Autowired
    private ShortLinkService shortLinkService;

    @PostMapping("add")
    public JsonData createShortLink(@RequestBody ShortLinkAddRequest request) {
        JsonData jsonData = shortLinkService.createShortLink(request);
        return jsonData;
    }

}

