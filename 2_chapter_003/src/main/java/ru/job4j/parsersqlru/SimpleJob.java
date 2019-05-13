package ru.job4j.parsersqlru;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;

public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("SimpleJob says: " + jobKey + " executing at " + new Date());
    }
}
