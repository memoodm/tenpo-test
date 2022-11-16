package co.com.memoodm.tenpo.service.history.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "history_http_event")
public class HttpEventEntity
{

    @Id
    private String id;
    private String sleuth;
    private String application;
    private String endpoint;

    @Enumerated(EnumType.STRING)
    private HttpMethod httpMethod;


    private String request;
    private String response;

    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date responseDate;

    private String error;

    public void updateNullValues(HttpEventEntity other){
        if(sleuth==null){ this.sleuth = other.getSleuth(); }
        if(application==null){ this.application = other.getApplication(); }
        if(endpoint==null){ this.endpoint = other.getEndpoint(); }
        if(httpMethod==null){ this.httpMethod = other.getHttpMethod(); }
        if(request ==null){ this.request = other.getRequest(); }
        if(response ==null){ this.response = other.getResponse(); }
        if(requestDate==null){ this.requestDate = other.getRequestDate(); }
        if(responseDate==null){ this.responseDate = other.getResponseDate(); }
        if(error==null){ this.error = other.getError(); }
    }

}
