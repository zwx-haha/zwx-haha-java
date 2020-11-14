package com.zwx.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Service(interfaceClass = HiService.class, version = "1.0.0")
@Component
public class HiServiceImpl implements HiService {

    @Override
    public String hi(String msg) {
        log.info("收到请求...{}", msg);
        return "ok~! I get msg: " + msg;
    }
}
