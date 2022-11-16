package co.com.memoodm.tenpo.service.calculator.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionsDictionary
{
    NO_SUBA_VALUE("Error getting the suba value","Calc001", HttpStatus.CONFLICT);

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
