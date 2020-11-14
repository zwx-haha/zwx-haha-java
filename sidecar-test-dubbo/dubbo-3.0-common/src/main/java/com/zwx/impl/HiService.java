package com.zwx.impl;

import com.zwx.IHiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-10-23 11:26
 */
@Slf4j
@Service
public class HiService implements IHiService {
    @Override
    public String sya(String msg) {
        return msg;
    }
}
