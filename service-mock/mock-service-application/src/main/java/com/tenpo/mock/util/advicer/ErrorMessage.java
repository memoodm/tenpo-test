package com.tenpo.mock.util.advicer;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorMessage {

	private Date date;
	private String message; 
	
}
