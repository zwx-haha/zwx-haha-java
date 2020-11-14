package com.zwx.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-10-28 15:20
 */
@RestController
public class HelloApi {

    @Value("${server.port}")
    private int port;

    @RequestMapping("hi")
    public String hi() {
        return "this is " + port;
    }
}
