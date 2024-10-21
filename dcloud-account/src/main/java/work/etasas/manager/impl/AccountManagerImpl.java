package work.etasas.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import work.etasas.manager.AccountManager;
import work.etasas.mapper.AccountMapper;
import work.etasas.model.AccountDO;

import java.util.List;

/**
 * @Title: AccountServiceImpl
 * @Author sas
 * @Package work.etasas.service.impl
 * @Date 2024/9/5 18:39
 */
@Component
@Slf4j
public class AccountManagerImpl implements AccountManager {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public int insert(AccountDO accountDO) {
        return accountMapper.insert(accountDO);
    }

    @Override
    public List<AccountDO> findByPhone(String phone) {

        List<AccountDO> accountDOList = accountMapper
                .selectList(new QueryWrapper<AccountDO>().eq("phone", phone));

        return accountDOList;
    }
}
