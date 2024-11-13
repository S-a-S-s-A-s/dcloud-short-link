package work.etasas.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import work.etasas.model.EventMessage;

import java.io.IOException;

/**
 * @author sas
 * @create 2024-11-12-16:51
 */
@Component
@Slf4j
@RabbitListener(queues = "short_link.error.queue")
public class ShortLinkErrorMQListener {

    @RabbitHandler
    public void shortLinkHandler(EventMessage eventMessage, Message message, Channel channel) throws IOException {
        log.error("告警：监听到消息ShortLinkErrorMQListener:{}",eventMessage);
        log.error("告警：Message:{}",message);
        log.error("告警成功，发送短信、邮件等告警通知");
    }
}
