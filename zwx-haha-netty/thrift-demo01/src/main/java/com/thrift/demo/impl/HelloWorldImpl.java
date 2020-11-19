package com.thrift.demo.impl;

import com.thrift.demo.HelloWorldService;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-11-15 17:17
 */
public class HelloWorldImpl implements HelloWorldService.Iface {

    @Override
    public String sayHello(String name) {
        System.out.println("client name:" + name);
        return "收到客户端消息->" + name;
    }
}
