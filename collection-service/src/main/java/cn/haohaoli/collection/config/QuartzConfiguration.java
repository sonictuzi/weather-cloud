package cn.haohaoli.collection.config;

import cn.haohaoli.collection.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Quartz配置
 * @author liwenhao
 */
@Configuration
public class QuartzConfiguration {

    /**
     * 定是任务更新频率
     */
    private static final int TIME = 300;

    //JobDetail
    @Bean
    public JobDetail weatherDataSyncJob(){
        return JobBuilder.newJob(WeatherDataSyncJob.class)
                .withIdentity("weatherDataSyncJob")
                .storeDurably()
                .build();
    }

    //Trigger
    @Bean
    public Trigger weatherDataTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(TIME)
                .repeatForever();
        return TriggerBuilder
                .newTrigger()
                .forJob(weatherDataSyncJob())
                .withIdentity("weatherDataTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

}
