package com.zwx.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zwx.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-10-22 11:04
 */
@Slf4j
@RestController
public class HelloController {


    @Reference(version = "1.0")
    private IHelloService helloService;

    @RequestMapping("hi/{name}")
    public String hi(@PathVariable String name) {
        log.info("收到请求--{}", name);
        return helloService.sayHello(name);
    }
}
