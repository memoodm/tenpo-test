package co.com.memoodm.tenpo.service.user.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EmailDuplicatedException extends RuntimeException implements CustomException
{

    private final String code;
    private final HttpStatus httpStatus;
    private final String description;

    public EmailDuplicatedException(String description)
    {
        super(ExceptionsDictionary.DUPLICATED_EMAIL.getMessage());
        this.code = ExceptionsDictionary.DUPLICATED_EMAIL.getCode();
        this.httpStatus = ExceptionsDictionary.DUPLICATED_EMAIL.getStatus();
        this.description = description;
    }

}
