package co.com.memoodm.tenpo.service.calculator.eventnotify;

import co.com.memoodm.tenpo.service.history.constants.QueueConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig
{

    @Bean
    public Queue queue()
    {
        return new Queue(QueueConstant.QUEUE_HTTP_EVENTS,true);
    }

}
