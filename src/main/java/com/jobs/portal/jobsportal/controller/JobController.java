package com.jobs.portal.jobsportal.controller;

import com.jobs.portal.jobsportal.business.base.CountryBusiness;
import com.jobs.portal.jobsportal.business.base.JobBusiness;
import com.jobs.portal.jobsportal.request.BaseRequest;
import com.jobs.portal.jobsportal.request.JobPostRequest;
import com.jobs.portal.jobsportal.response.BaseResponse;
import com.jobs.portal.jobsportal.service.base.JobService;
import com.jobs.portal.jobsportal.util.ConfigurationUtil;
import com.jobs.portal.jobsportal.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/portal")
public class JobController {

    @Autowired
    JobService service;

    @Autowired
    JobBusiness business;

    @Autowired
    CountryBusiness countryBusiness;

    @Autowired
    ConfigurationUtil configurationUtil;



    @PostMapping("/job-shift")
    public ResponseEntity<BaseResponse> login(@Valid @RequestBody BaseRequest request)throws Exception{

        countryBusiness.getJobShift(request);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE));
        baseResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
        baseResponse.setResponse(service.getJobShift());

        return ResponseEntity.ok(baseResponse);

    }

    @PostMapping("/job-type")
    public ResponseEntity<BaseResponse> getJobType(@Valid @RequestBody BaseRequest request)throws Exception{


        return ResponseEntity.ok(business.getJobType(request));

    }



    @PostMapping("/post-job")
    public ResponseEntity<BaseResponse> postJob(@Valid @RequestBody JobPostRequest request)throws Exception{

        Integer postJobId = service.postJob(request);
        BaseResponse baseResponse = new BaseResponse();
        if (postJobId != null) {
            baseResponse.setResponseMessage(configurationUtil.getMessage(Constants.SUCCESS_RESPONSE_CODE));
            baseResponse.setResponseCode(Constants.SUCCESS_RESPONSE_CODE);
        }else{
            baseResponse.setResponseMessage("something went wrong! we are looking at our end, please try again in sometime");
            baseResponse.setResponseCode("00200");
        }
        return ResponseEntity.ok(baseResponse);

    }

}
