package com.zwx;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.zwx.dubbo.HiService;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-10-25 16:24
 */
public class DubboConsume {
    public static void main(String[] args) {
        ReferenceConfig<HiService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("dubbo-demo-api-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        reference.setInterface(HiService.class);
//        reference.setUrl("dubbo://192.168.150.5:10020");
        reference.setVersion("1.0.0");
        HiService service = reference.get();


        HiService demoService = ReferenceConfigCache.getCache().get(reference);

        String i_am_dubbo = service.hi("i am dubbo");

        System.out.println("---------------------------------------------------" + i_am_dubbo);
    }
}
