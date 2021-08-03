package com.card.shuttle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/order")
@Controller
public class OrderViewCtl {

    @GetMapping("/main")
    public String orderMain(){
        return "/page/order/main";
    }
}
