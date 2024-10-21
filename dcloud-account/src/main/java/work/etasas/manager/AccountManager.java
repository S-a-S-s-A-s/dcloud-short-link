package work.etasas.manager;

import work.etasas.model.AccountDO;

import java.util.List;

/**
 * @Title: AccountService
 * @Author sas
 * @Package work.etasas.service
 * @Date 2024/9/5 18:38
 */
public interface AccountManager {
    int insert(AccountDO accountDO);

    List<AccountDO> findByPhone(String phone);
}
