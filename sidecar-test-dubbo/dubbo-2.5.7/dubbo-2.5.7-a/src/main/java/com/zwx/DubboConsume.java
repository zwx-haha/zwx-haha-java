package com.zwx;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.demo.DemoService;
import org.apache.dubbo.demo.Req;
import org.apache.dubbo.demo.Resp;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-10-25 16:24
 */
public class DubboConsume {
    public static void main(String[] args) {
        ReferenceConfig<DemoService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("dubbo-demo-api-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        reference.setInterface(DemoService.class);
        reference.setUrl("dubbo://192.168.150.5:10020");
        reference.setVersion("1.0.0");
        DemoService service = reference.get();


        DemoService demoService = ReferenceConfigCache.getCache().get(reference);
//        String message = demoService.sayHello("dubbo");
//        System.out.println("---------------------------------------------------" + message);
//
        Req req = new Req("13", "24");
        Resp resp = service.sayHello2(req, "haha");
        System.out.println("---------------------------------------------------" + resp);
        System.out.println("---------------------------------------------------" + resp.age);
        System.out.println("---------------------------------------------------" + resp.name);
    }
}
