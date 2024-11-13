package work.etasas.service;

import work.etasas.controller.request.ShortLinkAddRequest;
import work.etasas.util.JsonData;
import work.etasas.vo.ShortLinkVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 二当家小D
 * @since 2024-10-29
 */
public interface ShortLinkService  {

    ShortLinkVO queryByCode(String shortLinkCode);

    /**
     * 创建短链接
     * @param request
     * @return
     */
    JsonData createShortLink(ShortLinkAddRequest request);
}
