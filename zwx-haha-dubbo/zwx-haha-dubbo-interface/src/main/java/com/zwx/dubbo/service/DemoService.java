package com.zwx.dubbo.service;

import java.util.concurrent.CompletableFuture;

public interface DemoService {
    String sayHello(String name);

    default CompletableFuture<String> sayHelloAsync(String name) {
        return CompletableFuture.completedFuture(sayHello(name));
    }

    Resp sayHello2(Req req, String msg);
}
