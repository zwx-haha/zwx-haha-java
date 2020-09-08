package com.zwx.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: haha
 * @date : 2020-09-03 11:31
 */
@RestController
public class HelloApi {

    @RequestMapping("hi")
    public String hi() {
        return "hello world";
    }
}
