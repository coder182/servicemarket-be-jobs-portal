package com.jobs.portal.jobsportal.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobs.portal.jobsportal.business.base.CurrencyBussiness;
import com.jobs.portal.jobsportal.dto.CountryListJsonResponse;
import com.jobs.portal.jobsportal.dto.CurrencyListJsonResponse;
import com.jobs.portal.jobsportal.request.BaseRequest;
import com.jobs.portal.jobsportal.response.BaseResponse;
import com.jobs.portal.jobsportal.util.ConfigurationUtil;
import com.jobs.portal.jobsportal.util.Constants;
import com.jobs.portal.jobsportal.util.RestServiceUtility;

@Service
public class CurrencyBussinessImpl implements CurrencyBussiness {


    @Autowired
    ConfigurationUtil configurationUtil;

    @Autowired
    RestServiceUtility utility;


    ArrayList<String> listOfCurrencies = new ArrayList<String>();
	
	@Override
	public BaseResponse getCurrenciesName(BaseRequest request) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
        HttpHeaders header = new HttpHeaders();
        header.add("ContentType", "application/json");


        List currenciesResponse = (List) utility.callGetJson("https://api.nomics.com/v1/currencies?key=d0a7ba4aa83fa093b777e3085fa51a99&ids=BTC", ArrayList.class, header);
       List<CurrencyListJsonResponse> jsonResponseList = null;
        if(currenciesResponse !=null){

            jsonResponseList = mapper.convertValue(currenciesResponse, new TypeReference<List<CurrencyListJsonResponse>>(){});
            for(int i =0; i<jsonResponseList.size(); i++){

            	listOfCurrencies.add(jsonResponseList.get(i).getNameOfCurrency());
            }

            return  BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(listOfCurrencies).build();

        }else{

            return BaseResponse.builder().responseCode(Constants.SUCCESS_RESPONSE_CODE)
                    .responseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE)).response(null).build();
	}

}
	
}
