package work.etasas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import work.etasas.enums.BizCodeEnum;
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
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private FileService fileService;

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

}

