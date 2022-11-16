package co.com.memoodm.tenpo.service.history.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceedTopLimitResultsPerPageException extends RuntimeException implements CustomException
{
    private final String code;
    private final HttpStatus httpStatus;
    private final String description;

    public ExceedTopLimitResultsPerPageException(String description)
    {
        super(ExceptionsDictionary.EXCEED_LIMIT.getMessage());
        this.code = ExceptionsDictionary.EXCEED_LIMIT.getCode();
        this.httpStatus = ExceptionsDictionary.EXCEED_LIMIT.getStatus();
        this.description = description;
    }

}
