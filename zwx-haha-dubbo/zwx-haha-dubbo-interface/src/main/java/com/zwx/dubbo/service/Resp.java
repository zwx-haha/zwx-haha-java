package com.zwx.dubbo.service;

import java.io.Serializable;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-10-28 00:43
 */
public class Resp implements Serializable {
    public Resp(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String name;
    public String age;
}
