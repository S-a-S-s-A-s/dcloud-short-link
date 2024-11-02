package work.etasas.service.impl;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.etasas.manager.ShortLinkManager;
import work.etasas.model.ShortLinkDO;
import work.etasas.service.ShortLinkService;
import work.etasas.vo.ShortLinkVO;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 二当家小D
 * @since 2024-10-29
 */
@Service
public class ShortLinkServiceImpl  implements ShortLinkService {

    @Autowired
    private ShortLinkManager shortLinkManager;

    @Override
    public ShortLinkVO queryByCode(String shortLinkCode) {

        ShortLinkDO shortLinkDO = shortLinkManager.findByShortLinkCode(shortLinkCode);
        ShortLinkVO shortLinkVO = new ShortLinkVO();
        if(shortLinkDO == null){
            return null;
        }
        BeanUtils.copyProperties(shortLinkDO,shortLinkVO);
        return shortLinkVO;
    }
}
