package co.com.memoodm.tenpo.service.history.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpEvent implements Serializable
{

    private String id;
    private String sleuth;
    private String application;
    private String endpoint;
    private HttpMethod httpMethod;
    private String requestJson;
    private String responseJson;
    private Date requestDate;
    private Date responseDate;
    private String error;

}
