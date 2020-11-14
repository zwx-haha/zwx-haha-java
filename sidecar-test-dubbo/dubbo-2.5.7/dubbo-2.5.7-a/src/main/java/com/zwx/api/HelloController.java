package com.zwx.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zwx.IHelloService;
import com.zwx.dubbo.DubboThree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private DubboThree dubboThree;

    @Autowired
    private RestTemplate restT;

    @RequestMapping("say/{name}/{msg}")
    public String say(@PathVariable String name, @PathVariable String msg) {
        Map info = new HashMap();
        info.put("name", name);
        info.put("msg", msg);
        // 通过 Jackson JSON processing library 直接将返回值绑定到对象
        ResponseEntity<String> mapResponseEntity = restT.postForEntity("http://192.168.150.5:11000/", info, String.class);
        System.out.println(mapResponseEntity.getBody());
        return mapResponseEntity.getBody();
    }

    @RequestMapping("hi/{name}")
    public String hi(@PathVariable String name) {
        log.info("收到请求--{}", name);
        String hello = "";
        switch (name) {
            case "haha":
                hello = dubboThree.sayHello(name);
                break;
            default:
                hello = helloService.sayHello(name);
                break;
        }
        System.out.println("result :" + hello);
        return hello;
    }


}
