package ru.job4j.parsersqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class SqlRuJob implements Job {
    private static final Logger LOG = LogManager.getLogger(SqlRuJob.class.getName());
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOG.info("Запуск ParserSqlRu " + new Date());
        ParserSqlRu parserSqlRu = new ParserSqlRu();
        parserSqlRu.start();
        LOG.info("Завершение ParserSqlRu " + new Date());
    }
}
