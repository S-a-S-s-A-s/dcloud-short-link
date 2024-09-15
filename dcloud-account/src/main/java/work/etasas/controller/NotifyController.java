package work.etasas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.etasas.service.NotifyService;
import work.etasas.util.JsonData;

/**
 * @author sas
 * @create 2024-09-15-9:10
 */
@RestController
@RequestMapping("/api/account/v1")
public class NotifyController {

    @Autowired
    private NotifyService notifyService;

    @RequestMapping("send_code")
    public JsonData sendCode() {
        notifyService.testSend();
        return JsonData.buildSuccess();
    }
}
