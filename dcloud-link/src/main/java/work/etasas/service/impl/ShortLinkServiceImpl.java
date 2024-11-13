package work.etasas.service.impl;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.etasas.config.RabbitMQConfig;
import work.etasas.controller.request.ShortLinkAddRequest;
import work.etasas.enums.EventMessageType;
import work.etasas.interceptor.LoginInterceptor;
import work.etasas.manager.ShortLinkManager;
import work.etasas.model.EventMessage;
import work.etasas.model.ShortLinkDO;
import work.etasas.service.ShortLinkService;
import work.etasas.util.IdUtil;
import work.etasas.util.JsonData;
import work.etasas.util.JsonUtil;
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


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMQConfig rabbitMQConfig;

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

    @Override
    public JsonData createShortLink(ShortLinkAddRequest request) {

        long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();

        EventMessage eventMessage = EventMessage.builder().accountNo(accountNo)
                .content(JsonUtil.obj2Json(request))
                .messageId(IdUtil.generateSnowFlakeId().toString())
                .eventMessageType(EventMessageType.SHORT_LINK_ADD.name())
                .build();

        rabbitTemplate.convertAndSend(rabbitMQConfig.getShortLinkEventExchange(),rabbitMQConfig.getShortLinkAddRoutingKey(),eventMessage);


        return JsonData.buildSuccess();
    }
}
