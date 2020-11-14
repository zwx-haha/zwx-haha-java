package com.zwx;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * <p></p>
 *
 * @author: haha
 * @date : 2020-10-23 11:28
 */
public class App {
    public static void main(String[] args) throws IOException {
        //加载xml配置文件启动
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DubboConfiguration.class);
        context.start();
        System.in.read(); // 按任意键退出
    }
}
