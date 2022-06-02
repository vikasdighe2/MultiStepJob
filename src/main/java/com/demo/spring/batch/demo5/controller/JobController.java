package com.demo.spring.batch.demo5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.batch.demo5.runner.JobRunner;

/*
url: http://localhost:8080/run/job
 */

@RestController
@RequestMapping("/run")
public class JobController {

	@Autowired
    private JobRunner jobRunner;

	
	 @Autowired 
	 public JobController(JobRunner jobRunner) { 
		 this.jobRunner = jobRunner;
	 }
	 

    @RequestMapping(value = "/job")
    public String runJob() {
    	System.out.println("Get job Started");
        jobRunner.runBatchJob();
        System.out.println("Get job End");
        return String.format("Job Demo5 submitted successfully.");
    }
    
	
	 @RequestMapping(value = "/runFailJob") 
	 public String runFailJob(@RequestParam int id) { 
		 System.out.println("Get runFailJob Started");
		 jobRunner.runFailedBatchJob(id); 
		 System.out.println("Get runFailJob End");
		 return String.format("Failed Job "+id+" submitted successfully."); 
	 }
	 
}
