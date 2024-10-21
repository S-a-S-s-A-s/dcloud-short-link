package work.etasas.service;

import work.etasas.controller.request.AccountLoginRequest;
import work.etasas.controller.request.AccountRegisterRequest;
import work.etasas.util.JsonData;

/**
 * @Title: AccountService
 * @Author sas
 * @Package work.etasas.service
 * @Date 2024/9/5 18:38
 */
public interface AccountService {
    /**
     * 用户注册
     * @param accountRegisterRequest
     * @return
     */
    JsonData register(AccountRegisterRequest accountRegisterRequest);

    /**
     * 用户登录
     * @param accountLoginRequest
     * @return
     */
    JsonData login(AccountLoginRequest accountLoginRequest);
}
