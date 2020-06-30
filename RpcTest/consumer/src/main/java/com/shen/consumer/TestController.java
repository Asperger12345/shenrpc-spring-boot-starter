package com.shen.consumer;

import com.shen.rpc.ExampleService;
import com.shen.rpc.consumer.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Reference
    ExampleService exampleService;

    @GetMapping("/test")
    public String test(){
        return exampleService.info();
    }

}
