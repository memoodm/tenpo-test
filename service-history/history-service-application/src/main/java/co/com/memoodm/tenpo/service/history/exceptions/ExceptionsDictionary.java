package co.com.memoodm.tenpo.service.history.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionsDictionary
{
    EXCEED_LIMIT("The items by page value, exceed the allowed limit","Hist001", HttpStatus.NOT_ACCEPTABLE);

    private String message;
    private String code;
    private HttpStatus status;

    private ExceptionsDictionary(String message, String code, HttpStatus status)
    {
        this.message = message;
        this.code = code;
        this.status = status;
    }
}
