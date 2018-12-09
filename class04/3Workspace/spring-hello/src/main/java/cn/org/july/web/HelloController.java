package cn.org.july.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author goodboy
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }
}