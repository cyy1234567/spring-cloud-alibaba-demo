package com.example.democlient.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//服务名称
//@FeignClient(name = "demo-server",configuration = DemoServerFeignConfiguration.class)
@FeignClient(name = "demo-server")
public interface DemoServerFeignClient {
    @PostMapping("/server/get-user")
    public User getUser(@RequestBody User user);
}
