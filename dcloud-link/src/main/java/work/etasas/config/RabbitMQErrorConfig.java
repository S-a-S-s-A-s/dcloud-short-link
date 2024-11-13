package work.etasas.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sas
 * @create 2024-11-13-10:37
 */
@Configuration
@Slf4j
public class RabbitMQErrorConfig {

    private String shortLinkErrorExchange="short_link.error.exchange";

    private String shortLinkErrorQueue="short_link.error.queue";

    private String shortLinkErrorRoutingKey="short_link.error.routing.key";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public TopicExchange errorTopicExchange(){
        return new TopicExchange(shortLinkErrorExchange,true,false);
    }

    @Bean
    public Queue errorQueue(){
        return new Queue(shortLinkErrorQueue,true);
    }

    @Bean
    public Binding errorBinding(){
        return new Binding(shortLinkErrorQueue,Binding.DestinationType.QUEUE,shortLinkErrorExchange,shortLinkErrorRoutingKey,null);
    }

    @Bean
    public MessageRecoverer messageRecoverer(){
        return new RepublishMessageRecoverer(rabbitTemplate,shortLinkErrorExchange,shortLinkErrorRoutingKey);
    }

}
