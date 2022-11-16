package co.com.memoodm.tenpo.service.user.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserEmailDontExistException extends RuntimeException implements CustomException
{

    private final String code;
    private final HttpStatus httpStatus;
    private final String description;

    public UserEmailDontExistException(String description)
    {
        super(ExceptionsDictionary.NON_EXISTENCE_EMAIL.getMessage());
        this.code = ExceptionsDictionary.NON_EXISTENCE_EMAIL.getCode();
        this.httpStatus = ExceptionsDictionary.NON_EXISTENCE_EMAIL.getStatus();
        this.description = description;
    }

}
