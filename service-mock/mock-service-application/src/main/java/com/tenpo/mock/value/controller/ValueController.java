package com.tenpo.mock.value.controller;

import co.com.memoodm.tenpo.service.mock.web.client.MockValuesControllerClient;
import co.com.memoodm.tenpo.service.mock.web.endpoint.MockServiceEndpoints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenpo.mock.value.service.ValueService;

@Slf4j
@RestController
@RequestMapping(MockServiceEndpoints.PATH_VALUE)
public class ValueController implements MockValuesControllerClient
{

	@Autowired
	private ValueService valueService;

	@Override
	public ResponseEntity<Double> getPercentageValue()
	{
		System.out.println(Thread.currentThread() + " -> " + Thread.currentThread().getId());
		log.info("In getPercentageValue()");
		return new ResponseEntity<Double>(
				valueService.getValue(),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Double> updateValue(@PathVariable("percentage") Double percentage)
	{
		return new ResponseEntity<Double>(
				valueService.updatePercentage(percentage),
				HttpStatus.OK
		);
	}

	@Override
	public ResponseEntity<String> setSuccessStatus()
	{
		log.info("Status succed ON");
		valueService.setSuccessStatus();
		return new ResponseEntity<String>(
				"Service is set to success status",
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> setFailureStatus()
	{
		log.info("Status succed OFF");
		valueService.setFailureStatus();
		return new ResponseEntity<String>(
				"Service is set to failing status",
				HttpStatus.OK);
	}

}