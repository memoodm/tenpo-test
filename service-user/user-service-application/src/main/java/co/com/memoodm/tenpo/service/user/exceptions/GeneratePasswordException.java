package co.com.memoodm.tenpo.service.user.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GeneratePasswordException extends RuntimeException implements CustomException
{

    private final String code;
    private final HttpStatus httpStatus;
    private final String description;

    public GeneratePasswordException(String description)
    {
        super(ExceptionsDictionary.GENERATE_PASSWORD.getMessage());
        this.code = ExceptionsDictionary.GENERATE_PASSWORD.getCode();
        this.httpStatus = ExceptionsDictionary.GENERATE_PASSWORD.getStatus();
        this.description = description;
    }

}
