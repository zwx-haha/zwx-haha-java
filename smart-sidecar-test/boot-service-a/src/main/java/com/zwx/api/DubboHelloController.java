package com.zwx.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zwx.dubbo.HiService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-10-20 20:43
 */
@RestController
public class DubboHelloController {

    @Reference(version = "1.0.0")
    private HiService hiService;

    @RequestMapping("dubbo/hi/{msg}")
    public String hi(@PathVariable String msg) {
        return hiService.hi(msg);
    }
}
