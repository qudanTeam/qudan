package com.qudan.qingcloud.msqudan.cloudClients;

import com.netflix.hystrix.HystrixCommand;
import feign.Feign;
import feign.Retryer;
import feign.hystrix.HystrixFeign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by 汤云飞 on 2016/8/20.
 * FeignConfig 虽然是个配置类，但是它不应该被主上下文(ApplicationContext)扫描到(不要被 spring 扫描代理即可)，否则该类中的配置信息就会被应用于所有的 @FeignClient 客户端
 * (本例中 FeignConfig 中的配置应该只对使用了它的 client 起作用)。
 */
//@Configuration
public class FeignConfig extends FeignClientsConfiguration {

    @Configuration
    @ConditionalOnClass({ HystrixCommand.class, HystrixFeign.class })
    public static class HystrixFeignConfiguration {
        @Bean
        @Scope("prototype")
        @ConditionalOnMissingBean
        @ConditionalOnProperty(name = "feign.hystrix.enabled", matchIfMissing = true)
        public Feign.Builder feignHystrixBuilder() {
            return HystrixFeign.builder().retryer(new Retryer.Default(100,SECONDS.toMillis(1),1).clone());
        }
    }
}
