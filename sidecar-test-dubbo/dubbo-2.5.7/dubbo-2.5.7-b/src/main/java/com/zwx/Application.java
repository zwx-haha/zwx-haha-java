/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zwx;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import org.apache.dubbo.demo.DemoService;

import java.util.concurrent.CountDownLatch;

public class Application {
    public static void main(String[] args) throws Exception {
        startWithExport();
    }


    private static void startWithExport() throws InterruptedException {
        ServiceConfig<DemoServiceImpl> service = new ServiceConfig<DemoServiceImpl>();
        service.setInterface(DemoService.class);
        service.setRef(new DemoServiceImpl());
        service.setVersion("1.0.0");
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setPort(20881);
        protocol.setName("dubbo");
        service.setProtocol(protocol);

        service.setApplication(new ApplicationConfig("dubbo-demo-api-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        service.export();

        ServiceConfig<HelloService> service2 = new ServiceConfig<HelloService>();
        service2.setInterface(IHelloService.class);
        service2.setRef(new HelloService());
        service2.setVersion("1.0.0");
        service2.setProtocol(protocol);

        service2.setApplication(new ApplicationConfig("dubbo-demo-api-provider"));
        service2.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));

        service2.export();

        System.out.println("dubbo service started");
        new CountDownLatch(10).await();
    }


}
