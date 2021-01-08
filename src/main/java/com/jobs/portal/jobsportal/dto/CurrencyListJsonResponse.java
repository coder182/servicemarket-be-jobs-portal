package com.jobs.portal.jobsportal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown= true)
@Data
public class CurrencyListJsonResponse {

	private String nameOfCurrency;
}
