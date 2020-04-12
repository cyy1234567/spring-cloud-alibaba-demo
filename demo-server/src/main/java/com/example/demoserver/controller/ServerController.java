package com.example.demoserver.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
//import com.example.demoserver.MySource;
import com.example.demoserver.service.ServiceInterface;
import com.example.demoserver.User;
import lombok.AllArgsConstructor;
/*import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;*/
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;*/
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.example.demoserver.controller.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/server")
public class ServerController {
/*    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private Source source;
    @Autowired
    private MySource mySource;
    @Autowired
    private Sink sink;*/
    @Autowired
    private ServiceInterface serviceInterface;

    @GetMapping("gateway-test")
    public String test(@RequestParam(name = "msg",required = false) String msg){
        return "server-test"+msg;
    }

/*    @PostConstruct
    private void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule("/server/get-user02");
        // set limit qps to 20
        rule.setCount(3);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }*/
/*    @GetMapping("send-msg")
    public String sendMsg( @RequestParam(name = "msg",required = false) String msg){
        //mySource.output().send(MessageBuilder.withPayload("stream-test-topic-"+msg).build());
        //source.output().send(MessageBuilder.withPayload("stream-test-topic-"+msg).build());
        //rocketMQTemplate.convertAndSend("topic03", msg == null ? "test-msg" : msg);
*//*        rocketMQTemplate.sendMessageInTransaction(
                "tx-01-group",
                "topic03",
                MessageBuilder.withPayload(msg == null ? "test-msg" : msg)
                .setHeader(RocketMQHeaders.TRANSACTION_ID,"tid-000001")
                .setHeader("data_id","dataid-000001")
                .build(),
                "arg-000001"
                );*//*
        source.output().send(MessageBuilder.withPayload(msg == null ? "test-msg" : msg)
                .setHeader(RocketMQHeaders.TRANSACTION_ID,"tid-000001")
                .setHeader("data_id","dataid-000001")
                .setHeader("arg","arg=00001")
                .build());
        return msg;
    }*/

    @GetMapping("test/2")
    public String test() throws Exception{
        return this.serviceInterface.common("server-test-success");
    }

    @GetMapping("test/1")
    public String test2() throws Exception{
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



    //触发降级规则
    //1.6以后，只要是业务抛出异常，都执行这个方法，相当于熔断方法
    public String fallback(String a){
        return "降级";
    }


    @GetMapping("sentinel-api")
    @SentinelResource(value = "sentinel-api-resource",
            blockHandler = "blockHandler",
            blockHandlerClass = BlockTest.class,
            fallback = "fallback")
    public String sentinelApi(@RequestParam(name = "a",required = false) String a) {
        if (StringUtils.isEmpty(a)) {
            throw new IllegalArgumentException("a is null");
        }
        return a;
    }
}
