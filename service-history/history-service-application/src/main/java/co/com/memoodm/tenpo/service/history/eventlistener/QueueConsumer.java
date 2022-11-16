package co.com.memoodm.tenpo.service.history.eventlistener;

import co.com.memoodm.tenpo.service.history.constants.QueueConstant;
import co.com.memoodm.tenpo.service.history.dto.HttpEvent;
import co.com.memoodm.tenpo.service.history.service.HttpHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueConsumer
{

    @Autowired
    private HttpHistoryService httpHistoryService;

    @RabbitListener(queues = QueueConstant.QUEUE_HTTP_EVENTS)
    public void receive(@Payload HttpEvent message)
    {
        try{
            httpHistoryService.saveOrUpdate(message);
        }
        catch(Exception e){
            log.error("Cant save http log  message: " + message +", Error: "+e.getMessage());
        }

    }

}
