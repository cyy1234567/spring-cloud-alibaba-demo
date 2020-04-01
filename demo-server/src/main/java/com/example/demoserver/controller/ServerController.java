package com.example.demoserver.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.demoserver.service.ServiceInterface;
import com.example.demoserver.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/server")
public class ServerController {
    @Autowired
    private ServiceInterface serviceInterface;
    @GetMapping("test")
    public String test() throws Exception{
        return this.serviceInterface.common("server-test-success");
    }
    @PostMapping("get-user")
    public User getUser(@RequestBody User user){
        user.setName("0000000000000001");
        return user;
    }

    @GetMapping("get-user02")
    @SentinelResource("hot")
    public User getUser02( @RequestParam(name = "id",required = false) Long id,
                           @RequestParam(name = "name",required = false) String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        this.serviceInterface.common("");
        return user;
    }
}
