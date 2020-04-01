package com.example.demoserver;

import lombok.Data;
import sun.rmi.server.InactiveGroupException;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
}
