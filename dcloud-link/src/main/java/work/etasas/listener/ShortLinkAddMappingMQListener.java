package work.etasas.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import work.etasas.enums.BizCodeEnum;
import work.etasas.exception.BizException;
import work.etasas.model.EventMessage;

import java.io.IOException;

/**
 * @author sas
 * @create 2024-11-12-16:51
 */
@Component
@Slf4j
@RabbitListener(queues = "short_link.add.mapping.queue")
public class ShortLinkAddMappingMQListener {

    @RabbitHandler
    public void shortLinkHandler(EventMessage eventMessage, Message message, Channel channel) throws IOException {
        log.info("监听到消息ShortLinkAddMappingMQListener:{}",eventMessage);
        long tag = message.getMessageProperties().getDeliveryTag();
        try {
            //TODO
            // 业务逻辑
            // 1.新增短链
            // 2.新增短链映射
            // 3.发送消息
            // 4.手动ack

        } catch (Exception e) {
            log.error("消费失败{}", eventMessage);
            throw new BizException(BizCodeEnum.MQ_CONSUME_EXCEPTION);
        }
        log.info("消费成功{}", eventMessage);
        channel.basicAck(tag, false);
    }
}
