package work.etasas.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import work.etasas.util.JsonData;

/**
 * @Title: CustomExceptionHandler
 * @Author sas
 * @Package work.etasas.exception
 * @Date 2024/9/5 9:46
 */

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JsonData handler(Exception e) {
        if(e instanceof BizException) {
            BizException bizException = (BizException) e;
            log.error("[业务异常]{}", bizException);
            return JsonData.buildCodeAndMsg(bizException.getCode(), bizException.getMsg());
        } else {
            log.error("[系统异常]{}", e);
            return JsonData.buildError("全局异常，未知错误");
        }
    }
}
