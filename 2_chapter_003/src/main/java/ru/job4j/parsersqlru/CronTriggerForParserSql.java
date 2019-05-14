package ru.job4j.parsersqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.InputStream;
import java.util.Properties;

public class CronTriggerForParserSql {
    private static final Logger LOG = LogManager.getLogger(CronTriggerForParserSql.class.getName());

    public void doParsing() {
        try (InputStream in = CronTriggerForParserSql.class.getClassLoader()
                .getResourceAsStream("appJobMarketSQL.properties")) {
            Properties config = new Properties();
            config.load(in);
            String cronTime = config.getProperty("cron.time");
            System.out.println(cronTime);
            JobDetail job = JobBuilder.newJob(SqlRuJob.class).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("CronTrigger")
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronTime)).build();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        }
        catch (Exception e) {
            LOG.error("ERROR", e);
        }
    }

    public static void main(String[] args) {
        CronTriggerForParserSql parser = new CronTriggerForParserSql();
        parser.doParsing();
    }
}
