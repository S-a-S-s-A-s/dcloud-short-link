package work.etasas.manager;

import work.etasas.model.ShortLinkDO;

/**
 * @author sas
 * @create 2024-11-01-13:07
 */
public interface ShortLinkManager {

    int addShortLink(ShortLinkDO shortLinkDO);

    ShortLinkDO findByShortLinkCode(String shortLinkCode);

    int del(String shortLinkCode, Long accountNo);



}
