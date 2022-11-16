package co.com.memoodm.tenpo.service.history.config;

import co.com.memoodm.tenpo.service.history.dto.ErrorMessage;
import co.com.memoodm.tenpo.service.history.exceptions.CustomException;
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
                    .code("Hist000")
                    .message(e.getMessage())
                    .description("")
                    .build(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
