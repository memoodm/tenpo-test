package co.com.memoodm.tenpo.service.user.eventnotify;

import co.com.memoodm.tenpo.service.history.dto.HttpEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

@Service
public class HttpEventMapper
{

    @Autowired
    private Tracer tracer;

    @Autowired
    private HttpServletRequest request;

    ObjectMapper jsonMapper = new ObjectMapper();

    @Value("${spring.application.name}")
    private String appName;

    public HttpEvent beforeJointCreate(Object[] args) throws Exception
    {
        HttpEvent dto = base();
        dto.setRequestJson( Arrays.toString(args) );
        dto.setRequestDate( new Date() );
        return dto;
    }

    public HttpEvent afterJointCreate(Object object) throws JsonProcessingException {
        HttpEvent dto = base();
        dto.setResponseJson( object.toString() );
        dto.setResponseDate( new Date() );
        return dto;
    }

    public HttpEvent inError(Exception exception)
    {
        HttpEvent dto = base();
        dto.setError( exception.getMessage() );
        return dto;
    }

    private HttpEvent base()
    {
        return HttpEvent.builder()
                .id( appName+":"+tracer.currentSpan().context().spanId() )
                .sleuth( tracer.currentSpan().context().spanId() )
                .application( appName )
                .endpoint( request.getRequestURI() + ( request.getQueryString()!=null ? "?"+request.getQueryString() : "" ) )
                .httpMethod( HttpMethod.valueOf(request.getMethod()) )
                .build();
    }

}
