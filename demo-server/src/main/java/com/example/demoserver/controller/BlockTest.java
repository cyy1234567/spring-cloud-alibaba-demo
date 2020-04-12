package com.example.demoserver.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class BlockTest {
    //触发流控规则
    public static String blockHandler(String a, BlockException e){
        return "流控";
    }
}
