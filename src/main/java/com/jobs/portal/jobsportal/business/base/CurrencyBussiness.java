package com.jobs.portal.jobsportal.business.base;

import com.jobs.portal.jobsportal.request.BaseRequest;
import com.jobs.portal.jobsportal.response.BaseResponse;

public interface CurrencyBussiness {
	BaseResponse getCurrenciesName(BaseRequest request) throws Exception;

}
