package com.zwx.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.dubbo.demo.DemoService;
import org.apache.dubbo.demo.Req;
import org.apache.dubbo.demo.Resp;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-10-23 12:33
 */
@Component("dubboThree")
public class DubboThree implements DemoService {
    @Reference(version = "1.0.0")
    private DemoService demoService;

    @Override
    public String sayHello(String name) {
        return demoService.sayHello(name);
    }

    @Override
    public CompletableFuture<String> sayHelloAsync(String name) {
        return null;
    }

    @Override
    public Resp sayHello2(Req req, String msg) {
        return null;
    }


}
