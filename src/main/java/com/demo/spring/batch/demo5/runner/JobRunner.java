package com.demo.spring.batch.demo5.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.demo.spring.batch.demo5.utils.Constants;

import java.util.Date;

@Component
public class JobRunner {

    private static final Logger logger = LoggerFactory.getLogger(JobRunner.class);

    @Autowired
    JobOperator jobOperator;
    
    @Autowired
    JobExplorer jobExplorer;

    private JobLauncher simpleJobLauncher;
    private Job demo1;

    @Autowired
    public JobRunner(Job demo1, JobLauncher jobLauncher) {
        this.simpleJobLauncher = jobLauncher;
        this.demo1 = demo1;
    }


    @Async
    public void runBatchJob() {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString(Constants.FILE_NAME_CONTEXT_KEY, "employees.csv");
        jobParametersBuilder.addDate("date", new Date(), true);
        runJob(demo1, jobParametersBuilder.toJobParameters());
    }
    
    @Async
    public void runFailedBatchJob(int executionId) {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString(Constants.FILE_NAME_CONTEXT_KEY, "employees.csv");
        jobParametersBuilder.addDate("date", new Date(), true);
        //runJob(demo1, jobParametersBuilder.toJobParameters());
        
        try {
        	System.out.println("Failed Job Started "+ executionId);
			Long restartId = jobOperator.restart(executionId);
			System.out.println("Failed Job After restartId "+ restartId);
			JobExecution restartExecution = jobExplorer.getJobExecution(restartId);
			String restartExecutionn = ""+restartExecution.getStatus();
			System.out.println("Failed Job After restartExecution id "+ restartExecution.getStatus());
		} catch (JobInstanceAlreadyCompleteException | NoSuchJobExecutionException | NoSuchJobException
				| JobRestartException | JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }


    public void runJob(Job job, JobParameters parameters) {
        try {
            JobExecution jobExecution = simpleJobLauncher.run(job, parameters);
            System.out.println("Final Job Status "+ jobExecution.getStatus());
        } catch (JobExecutionAlreadyRunningException e) {
            logger.info("Job with fileName={} is already running.", parameters.getParameters().get(Constants.FILE_NAME_CONTEXT_KEY));
        } catch (JobRestartException e) {
            logger.info("Job with fileName={} was not restarted.", parameters.getParameters().get(Constants.FILE_NAME_CONTEXT_KEY));
        } catch (JobInstanceAlreadyCompleteException e) {
            logger.info("Job with fileName={} already completed.", parameters.getParameters().get(Constants.FILE_NAME_CONTEXT_KEY));
        } catch (JobParametersInvalidException e) {
            logger.info("Invalid job parameters.", parameters.getParameters().get(Constants.FILE_NAME_CONTEXT_KEY));
        }
    }


}
