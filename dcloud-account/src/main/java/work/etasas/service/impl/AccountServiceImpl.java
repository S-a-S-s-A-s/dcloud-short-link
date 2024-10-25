package work.etasas.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.etasas.controller.request.AccountLoginRequest;
import work.etasas.controller.request.AccountRegisterRequest;
import work.etasas.enums.AuthTypeEnum;
import work.etasas.enums.BizCodeEnum;
import work.etasas.enums.SendCodeEnum;
import work.etasas.manager.AccountManager;
import work.etasas.model.AccountDO;
import work.etasas.model.LoginUser;
import work.etasas.service.AccountService;
import work.etasas.service.NotifyService;
import work.etasas.util.CommonUtil;
import work.etasas.util.IdUtil;
import work.etasas.util.JWTUtil;
import work.etasas.util.JsonData;

import java.util.List;

/**
 * @Title: AccountServiceImpl
 * @Author sas
 * @Package work.etasas.service.impl
 * @Date 2024/9/5 18:39
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private AccountManager accountManager;

    /**
     * 手机验证码验证
     * 密码加密 TODO
     * 账号唯一性检查 TODO
     * 插入数据库
     * 新注册用户福利发放 TODO
     * @param accountRegisterRequest
     * @return
     */
    @Override
    public JsonData register(AccountRegisterRequest accountRegisterRequest) {

        boolean checkCode = false;
        if(StringUtils.isNotBlank(accountRegisterRequest.getPhone())) {
            checkCode = notifyService.checkCode(SendCodeEnum.USER_REGISTER, accountRegisterRequest.getPhone(), accountRegisterRequest.getCode());
        }
        if(!checkCode) {
            return JsonData.buildResult(BizCodeEnum.CODE_ERROR);
        }
        AccountDO accountDO = new AccountDO();
        BeanUtils.copyProperties(accountRegisterRequest, accountDO);

        //认证级别
        accountDO.setAuth(AuthTypeEnum.DEFAULT.name());

        //生成唯一的账号
        accountDO.setAccountNo(Long.valueOf(IdUtil.generateSnowFlakeId().toString()));

        //设置盐 密码
        accountDO.setSecret("$1$" + CommonUtil.getStringNumRandom(8));
        String cryptPwd = Md5Crypt.md5Crypt(accountDO.getPwd().getBytes(), accountDO.getSecret());
        accountDO.setPwd(cryptPwd);

        int rows = accountManager.insert(accountDO);
        log.info("rows:{},注册成功:{}", rows,accountDO);

        //新注册用户福利发放
        userRegisterInit(accountDO);
        return JsonData.buildSuccess();
    }

    /**
     * 用户登录
     *
     * 1.根据手机号查询用户
     * 2.
     *
     * @param accountLoginRequest
     * @return
     */
    @Override
    public JsonData login(AccountLoginRequest accountLoginRequest) {

        List<AccountDO> accountDOList = accountManager.findByPhone(accountLoginRequest.getPhone());

        if(accountDOList!=null && accountDOList.size() == 1) {
            AccountDO accountDO = accountDOList.get(0);
            String cryptPwd = Md5Crypt.md5Crypt(accountLoginRequest.getPwd().getBytes(), accountDO.getSecret());
            if(cryptPwd.equals(accountDO.getPwd())) {

                LoginUser loginUser = LoginUser.builder().build();
                BeanUtils.copyProperties(accountDO, loginUser);

                //生成TOKEN
                String token = JWTUtil.createToken(loginUser);

                return JsonData.buildSuccess(token);
            }else{
                return JsonData.buildResult(BizCodeEnum.ACCOUNT_PWD_ERROR);
            }
        }else{
            return JsonData.buildResult(BizCodeEnum.ACCOUNT_UNREGISTER);
        }

    }

    /**
     * 新注册用户福利发放 TODO
     * @param accountDO
     */
    private void userRegisterInit(AccountDO accountDO) {
    }
}
