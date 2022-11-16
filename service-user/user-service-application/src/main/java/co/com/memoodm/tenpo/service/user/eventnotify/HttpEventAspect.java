package co.com.memoodm.tenpo.service.user.eventnotify;

import co.com.memoodm.tenpo.service.history.dto.HttpEvent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class HttpEventAspect
{
    @Autowired
    private HttpEventMapper mapper;

    @Autowired
    private QueueProducer queueConsumer;

    @Pointcut("execution(* co.com.memoodm.tenpo.service.user.controller.*.*(..))")
    public void loggingPointCut()
    {
    }

    @Before("loggingPointCut()")
    public void before(JoinPoint point)
    {
        try {
            HttpEvent httpEvent = mapper.beforeJointCreate(point.getArgs());
            queueConsumer.send(httpEvent);
        }
        catch(Exception e){
            log.error("creating http event for queue in before loggingPointCut");
        }

    }

    @AfterReturning(value="loggingPointCut()", returning = "response")
    public void afterReturning(JoinPoint point, Object response)
    {
        try {
            HttpEvent httpEvent = mapper.afterJointCreate(response);
            queueConsumer.send(httpEvent);
        }
        catch(Exception e){
            log.error("creating http event for queue in afterReturning loggingPointCut");
        }
    }

    @AfterThrowing(value="loggingPointCut()", throwing = "exception")
    public void afterThrowing(JoinPoint point, Exception exception)
    {
        HttpEvent httpEvent = mapper.inError(exception);
        queueConsumer.send(httpEvent);
    }

}
