package work.etasas.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import work.etasas.manager.ShortLinkManager;
import work.etasas.mapper.ShortLinkMapper;
import work.etasas.model.ShortLinkDO;

/**
 * @author sas
 * @create 2024-11-01-13:07
 */
@Component
@Slf4j
public class ShortLinkManagerImpl implements ShortLinkManager {

    @Autowired
    private ShortLinkMapper shortLinkMapper;

    @Override
    public int addShortLink(ShortLinkDO shortLinkDO) {
        return shortLinkMapper.insert(shortLinkDO);
    }

    @Override
    public ShortLinkDO findByShortLinkCode(String shortLinkCode) {

        ShortLinkDO shortLinkDO = shortLinkMapper.selectOne(new QueryWrapper<ShortLinkDO>().eq("code", shortLinkCode));

        return shortLinkDO;
    }

    @Override
    public int del(String shortLinkCode, Long accountNo) {

        ShortLinkDO shortLinkDO = new ShortLinkDO();
        shortLinkDO.setDel(1);

        int rows = shortLinkMapper.update(shortLinkDO, new QueryWrapper<ShortLinkDO>().eq("code", shortLinkCode).eq("account_no", accountNo));

        return rows;
    }
}
