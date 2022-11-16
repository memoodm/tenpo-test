package co.com.memoodm.tenpo.service.user.eventnotify;

import co.com.memoodm.tenpo.service.history.dto.HttpEvent;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class QueueProducer
{

    private Queue queue;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public QueueProducer(
            Queue queue,
            RabbitTemplate rabbitTemplate
    )
    {
        this.queue = queue;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Async
    public void send(HttpEvent dto)
    {
        rabbitTemplate.convertAndSend( queue.getName(), dto );
    }

}
