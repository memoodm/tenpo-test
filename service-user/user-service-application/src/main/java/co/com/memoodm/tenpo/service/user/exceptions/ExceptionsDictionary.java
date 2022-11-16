package co.com.memoodm.tenpo.service.user.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionsDictionary
{

    GENERATE_PASSWORD("Error by encoding the password","001", HttpStatus.NOT_ACCEPTABLE),
    NON_EXISTENCE_EMAIL("User email have no record on repository","002", HttpStatus.NOT_FOUND),
    WRONG_USER_PASSWORD("Wrong user password","003", HttpStatus.BAD_REQUEST),
    DUPLICATED_EMAIL("Email already exist","004", HttpStatus.NOT_ACCEPTABLE);

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
