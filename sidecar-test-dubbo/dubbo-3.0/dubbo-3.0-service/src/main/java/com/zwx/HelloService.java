package com.zwx;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-10-22 10:55
 */
@Slf4j
@Service(version = "1.0")
public class HelloService implements IHelloService {
    @Override
    public String sayHello(String name) {
        log.info("收到dubbo2.5.7版本请求---{}", name);
        return "Hello:" + name + "你好，你好~~";
    }
}
