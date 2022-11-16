package co.com.memoodm.tenpo.service.calculator.config;

import co.com.memoodm.tenpo.service.calculator.exception.CustomException;
import co.com.memoodm.tenpo.service.calculator.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceRestController
{
    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<ErrorMessage> handleExceptions(Exception e)
    {
        if( e instanceof CustomException)
        {
            CustomException customException = (CustomException)e;
            return new ResponseEntity(ErrorMessage.builder()
                    .code(customException.getCode())
                    .message(customException.getMessage())
                    .description(customException.getDescription())
                    .build(),
                    customException.getHttpStatus());
        }
        else
        {
            return new ResponseEntity(ErrorMessage.builder()
                    .code("000")
                    .message(e.getMessage())
                    .build(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
