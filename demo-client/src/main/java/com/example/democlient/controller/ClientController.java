package com.example.democlient.controller;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.convert.NacosConfigConverter;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.NacosConfigService;
import java.util.concurrent.Executor;
import javax.annotation.PostConstruct;

import com.example.democlient.FeignClient.DemoServerFeignClient;
import com.example.democlient.FeignClient.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
@RequestMapping("/client")
public class ClientController {
    @Value("${username}")
    private String username;
    @Autowired
    private DemoServerFeignClient demoServerFeignClient;

    @Autowired
    private NacosConfigManager nacosConfigManager;


    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("test")
    public String test(){
        User user = new User();
        user.setId(200000L);
        user.setName("tom2000000000");
        return demoServerFeignClient.getUser(user).toString();
    }
    @PostConstruct
    public String listener(){
        try {
            nacosConfigManager.getConfigService().addListener("demo-client-dev.yaml","DEFAULT_GROUP",new Listener() {
                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        System.out.println("recieve:" + configInfo);
                    }

                    @Override
                    public Executor getExecutor() {
                        return null;
                    }
                });
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return "listener";
    }
}
