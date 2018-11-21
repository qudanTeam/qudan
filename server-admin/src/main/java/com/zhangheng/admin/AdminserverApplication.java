package com.zhangheng.admin;

//import com.hazelcast.config.Config;
//import com.hazelcast.config.EvictionPolicy;
//import com.hazelcast.config.ListConfig;
//import com.hazelcast.config.MapConfig;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 蜡笔小新不爱吃青椒 on 2018/7/31.
 */

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableAdminServer
public class AdminserverApplication {

//    @Bean
//    public Config hazelcastConfig() {
//        return new Config().setProperty("hazelcast.jmx", "true")
//                .addMapConfig(new MapConfig("spring-boot-admin-application-store").setBackupCount(1)
//                        .setEvictionPolicy(EvictionPolicy.NONE))
//                .addListConfig(new ListConfig("spring-boot-admin-event-store").setBackupCount(1)
//                        .setMaxSize(1000));
//    }


    public static void main(String[] args) {
        SpringApplication.run(AdminserverApplication.class, args);
    }
}
