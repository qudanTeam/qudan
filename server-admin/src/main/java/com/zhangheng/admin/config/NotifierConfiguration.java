//package com.zhangheng.admin.config;
//
//import de.codecentric.boot.admin.notify.Notifier;
//import de.codecentric.boot.admin.notify.RemindingNotifier;
//import de.codecentric.boot.admin.notify.filter.FilteringNotifier;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
///**
// * Created by 蜡笔小新不爱吃青椒 on 2018/7/31.
// *
// * 为监控服务添加宕机邮件通知
// */
//
//@Configuration
//@EnableScheduling
//public class NotifierConfiguration {
//
//    @Autowired
//    private Notifier notifier;
//
//    //服务上线或者下线都通知
//    private String[] reminderStatuses = { "UP","DOWN","UNKNOWN","OFFLINE" };
////    private String[] reminderStatuses = { "DOWN" };
//
//    @Bean
//    public FilteringNotifier filteringNotifier() {
//        return new FilteringNotifier(notifier);
//    }
//
//
//
//    @Bean
//    @Primary
//    public RemindingNotifier remindingNotifier() {
//        RemindingNotifier remindingNotifier = new RemindingNotifier(notifier);
//
////        RemindingNotifier remindingNotifier = new RemindingNotifier(filteringNotifier());
//        //设定时间，5分钟提醒一次
////        remindingNotifier.setReminderPeriod(TimeUnit.MINUTES.toMillis(5));
//        //设定监控服务状态，状态改变为给定值的时候提醒
//        remindingNotifier.setReminderStatuses(reminderStatuses);
//        return remindingNotifier;
//    }
//
//    //每6秒轮询一次
//    @Scheduled(fixedRate = 60_000L)
//    public void remind() {
//        remindingNotifier().sendReminders();
//    }
//
//}
