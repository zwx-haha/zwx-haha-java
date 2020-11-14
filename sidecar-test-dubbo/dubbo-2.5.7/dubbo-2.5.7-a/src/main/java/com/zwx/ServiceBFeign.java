//package com.zwx;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Map;
//
///**
// * <p></p>
// *
// * @author: haha
// * @date : 2020-10-23 13:00
// */
//@FeignClient(value = "s-b")
//public interface ServiceBFeign {
//
//    @RequestMapping(value = "/hi")
//    public String hi(@RequestParam String msg);
//
//    @RequestMapping("say")
//    public String say(@RequestBody Map info);
//}
