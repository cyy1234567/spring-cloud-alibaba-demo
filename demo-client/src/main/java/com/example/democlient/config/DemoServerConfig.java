package com.example.democlient.config;

import com.ribbon.config.RibbonConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClient(name ="demo-server", configuration = RibbonConfig.class)
public class DemoServerConfig {
}
