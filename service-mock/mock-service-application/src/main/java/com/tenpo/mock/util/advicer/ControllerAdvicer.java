package com.tenpo.mock.util.advicer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tenpo.mock.util.exceptions.SimulateServerErrorException;

import java.util.Date;

@RestControllerAdvice
public class ControllerAdvicer {
	
	@ExceptionHandler(value=SimulateServerErrorException.class)
	public ResponseEntity<ErrorMessage> handleSimulatedErrorExceptions(SimulateServerErrorException e){
		ErrorMessage errorMessage = ErrorMessage.builder()
									.date(new Date())
									.message("Simulated error")
									.build();
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.FORBIDDEN);
	}

}
