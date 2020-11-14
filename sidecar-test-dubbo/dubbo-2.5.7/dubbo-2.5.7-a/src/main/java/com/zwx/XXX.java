package com.zwx;

import com.alibaba.dubbo.config.annotation.Reference;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-10-28 01:39
 */
public interface XXX {

    @Reference(url = "")
    public void say();

}
