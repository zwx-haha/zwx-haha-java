//package com.zwx.api;
//
//import com.zwx.feign.ServiceBFeign;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * <p></p>
// *
// * @author: haha
// * @date : 2020-10-20 20:43
// */
//@RestController
//public class HelloController {
//
//    @Autowired
//    private ServiceBFeign serviceBFeign;
//
//    @RequestMapping("hi/{msg}")
//    public String hi(@PathVariable String msg) {
//        return serviceBFeign.hi(msg);
//    }
//
//    @RequestMapping("say/{msg}")
//    public String say(@PathVariable String msg) {
//        Map info = new HashMap();
//        info.put("name", "s-b");
//        info.put("msg", msg);
//        return serviceBFeign.say(info);
//    }
//}
