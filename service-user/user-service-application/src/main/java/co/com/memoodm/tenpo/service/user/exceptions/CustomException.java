package co.com.memoodm.tenpo.service.user.exceptions;

import org.springframework.http.HttpStatus;

public interface CustomException
{
    String getCode();
    String getMessage();
    HttpStatus getHttpStatus();
    String getDescription();
}
