package work.etasas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import work.etasas.controller.request.AccountLoginRequest;
import work.etasas.controller.request.AccountRegisterRequest;
import work.etasas.enums.BizCodeEnum;
import work.etasas.service.AccountService;
import work.etasas.service.FileService;
import work.etasas.util.JsonData;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 二当家小D
 * @since 2024-09-05
 */
@RestController
@RequestMapping("/api/account/v1")
public class AccountController {

    @Autowired
    private FileService fileService;

    @Autowired
    private AccountService accountService;

    /**
     * 上传用户头像 最大默认1M
     * @return
     */
    @PostMapping("upload")
    public JsonData uploadUserImg(@RequestPart("file") MultipartFile file) {
        String result = fileService.uploadUserImg(file);
        if (result != null) {
            return JsonData.buildSuccess(result);
        }else {
            return JsonData.buildResult(BizCodeEnum.FILE_UPLOAD_USER_IMG_FAIL);
        }
    }

    /**
     * 用户注册
     * @param accountRegisterRequest
     * @return
     */
    @PostMapping("register")
    public  JsonData register(@RequestBody AccountRegisterRequest accountRegisterRequest) {

        JsonData jsonData = accountService.register(accountRegisterRequest);
        return jsonData;
    }

    /**
     * 用户登录
     * @param accountLoginRequest
     * @return
     */
    @PostMapping("login")
    public JsonData login(@RequestBody AccountLoginRequest accountLoginRequest) {
        return accountService.login(accountLoginRequest);

    }

}

