package com.ribbon.config;

import com.example.democlient.config.NacosWeightedRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfig {
    @Bean
    public IRule getRule(){
        return new NacosWeightedRule();
    }

    @Bean
    public IPing getIPing(){
        return new PingUrl();
    }
}
