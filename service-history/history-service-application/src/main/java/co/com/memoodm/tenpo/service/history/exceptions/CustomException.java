package co.com.memoodm.tenpo.service.history.exceptions;

import org.springframework.http.HttpStatus;

public interface CustomException
{
    String getCode();
    String getMessage();
    HttpStatus getHttpStatus();
    String getDescription();
}
