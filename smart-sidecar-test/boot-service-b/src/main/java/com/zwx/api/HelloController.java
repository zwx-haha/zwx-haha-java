package com.zwx.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-10-20 20:43
 */
@RestController
public class HelloController {

    @RequestMapping("hi")
    public String hi(@RequestParam String msg) {
        return "OK~! I get msg: " + msg;
    }

    @RequestMapping("say")
    public String say(@RequestBody Map info) {
        return "OK~! I get msg: " + info;
    }
}
