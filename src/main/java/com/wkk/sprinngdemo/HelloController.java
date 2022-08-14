package com.wkk.sprinngdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 类描述
 *
 * @author weikunkun
 * @since 2022/7/14
 */
@RestController
public class HelloController {

    @Autowired
    private ShoppingService shoppingService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "this is a demo";
    }

    @RequestMapping(value = "/online/shopping", method = RequestMethod.GET)
    public String buy(String name) {
        shoppingService.buy(name);
        return "success";
    }

}
