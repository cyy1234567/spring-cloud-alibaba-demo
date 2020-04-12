package com.example.demoserver.controller;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyUrlBlockHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        ErrorMsg msg = ErrorMsg.builder().msg("默认").build();
        if(e instanceof FlowException){
            msg = ErrorMsg.builder().msg("流控").build();
        }else if(e instanceof DegradeException){
            msg = ErrorMsg.builder().msg("降级").build();
        }else if(e instanceof ParamFlowException){
            msg = ErrorMsg.builder().msg("热点参数").build();
        }else if(e instanceof SystemBlockException){
            msg = ErrorMsg.builder().msg("系统规则").build();
        }else if(e instanceof AuthorityException){
            msg = ErrorMsg.builder().msg("来源授权").build();
        }
        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("utf8");
         new ObjectMapper().writeValue(httpServletResponse.getWriter(),msg);
    }
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class ErrorMsg{
    private String msg;
}