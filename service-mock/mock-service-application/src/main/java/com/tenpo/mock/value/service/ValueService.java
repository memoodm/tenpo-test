package com.tenpo.mock.value.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tenpo.mock.util.exceptions.SimulateServerErrorException;

import java.util.Date;

@Service
public class ValueService {

	protected Boolean forceFailing;
	
	@Value("${mock.starting.value.percentage}")
	protected Double percentage;
	
	@Autowired
	public ValueService() {
		forceFailing = false;
	}
	
	public Double getValue() {
		System.out.println(Thread.currentThread() + " -> " + Thread.currentThread().getId());
		if(forceFailing) {
			throw new SimulateServerErrorException();
		}
		return percentage;
	}
	
	public Double updatePercentage(Double percentage) {
		return this.percentage = percentage;
	}
	
	public void setSuccessStatus() {
		this.forceFailing = false;
	}
	
	public void setFailureStatus() {
		this.forceFailing = true;
	}
		
}
