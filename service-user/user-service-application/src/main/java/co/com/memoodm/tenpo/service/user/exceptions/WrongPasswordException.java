package co.com.memoodm.tenpo.service.user.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WrongPasswordException extends RuntimeException implements CustomException
{

    private final String code;
    private final HttpStatus httpStatus;
    private final String description;

    public WrongPasswordException(String description)
    {
        super(ExceptionsDictionary.WRONG_USER_PASSWORD.getMessage());
        this.code = ExceptionsDictionary.WRONG_USER_PASSWORD.getCode();
        this.httpStatus = ExceptionsDictionary.WRONG_USER_PASSWORD.getStatus();
        this.description = description;
    }

}
