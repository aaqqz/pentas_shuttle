package com.card.shuttle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/order")
@Controller
public class OrderViewCtl {

    @Autowired
    CommonCtl commonCtl;

    @GetMapping("/order")
    public String order(){
        return "page/order/order";
    }
    @GetMapping("/order_de")
    public String orderDetail(){
        return "page/order/order_de";
    }
    @GetMapping("/order_cart")
    public String orderCart(HttpServletRequest request){
        System.out.println(commonCtl.langSet(request));
        return "page/order/order_cart";
    }
    @GetMapping("/order_visit")
    public String orderVisit(){
        return "page/order/order_visit";
    }
}
