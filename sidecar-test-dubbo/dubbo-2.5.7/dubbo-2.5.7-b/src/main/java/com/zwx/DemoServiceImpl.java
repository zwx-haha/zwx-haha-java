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

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.demo.DemoService;
import org.apache.dubbo.demo.Req;
import org.apache.dubbo.demo.Resp;

@Slf4j
@Service(version = "1.0.0")
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        log.info("收到请求 sayHello  " + name);
        return "Hello " + name + ", response from provider: ";
    }


    @Override
    public Resp sayHello2(Req req, String msg) {
        log.info("收到请求 sayHello2  " + req + msg);
        return new Resp("hihi", "hehe");
    }

}
