package com.shen.provider.service;

import com.shen.rpc.ExampleService;
import com.shen.rpc.provider.Service;


@Service
public class ExampleServiceImpl implements ExampleService {
    public String info() {
        return "example";
    }
}
