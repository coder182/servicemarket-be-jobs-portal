package com.jobs.portal.jobsportal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobs.portal.jobsportal.business.base.CurrencyBussiness;
import com.jobs.portal.jobsportal.request.BaseRequest;
import com.jobs.portal.jobsportal.response.BaseResponse;

@RestController
@RequestMapping("/list")
public class CurrencyController {

	@Autowired
	CurrencyBussiness currencyBussines;
	
	@PostMapping("/currncies")
	public ResponseEntity<BaseResponse> getListOfCurrencies(@Valid @RequestBody BaseRequest request) throws Exception{
		return ResponseEntity.ok(currencyBussines.getCurrenciesName(request));
	}
}
