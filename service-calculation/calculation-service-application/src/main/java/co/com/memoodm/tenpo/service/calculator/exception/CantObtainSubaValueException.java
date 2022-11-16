package co.com.memoodm.tenpo.service.calculator.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CantObtainSubaValueException extends RuntimeException implements CustomException
{
    private final String code;
    private final HttpStatus httpStatus;
    private final String description;

    public CantObtainSubaValueException(String description)
    {
        super(ExceptionsDictionary.NO_SUBA_VALUE.getMessage());
        this.code = ExceptionsDictionary.NO_SUBA_VALUE.getCode();
        this.httpStatus = ExceptionsDictionary.NO_SUBA_VALUE.getStatus();
        this.description = description;
    }
}
