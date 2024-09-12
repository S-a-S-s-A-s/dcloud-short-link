package work.etasas.exception;

import lombok.Data;
import work.etasas.enums.BizCodeEnum;

/**
 * @Title: BizException
 * @Author sas
 * @Package work.etasas.exception
 * @Date 2024/9/5 9:43
 */
@Data
public class BizException extends RuntimeException{
    private int code;
    private String msg;

    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public  BizException(BizCodeEnum bizCodeEnum) {
        super(bizCodeEnum.getMessage());
        this.code = bizCodeEnum.getCode();
        this.msg = bizCodeEnum.getMessage();
    }


}
