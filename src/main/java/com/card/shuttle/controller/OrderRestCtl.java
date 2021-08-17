package com.card.shuttle.controller;

import com.card.shuttle.common.module.util.DevMap;
import com.card.shuttle.service.OrderSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class OrderRestCtl {

    @Autowired
    OrderSvc orderSvc;

    @PostMapping("/order/order_card/payment")
    public DevMap payment(@RequestBody DevMap param, HttpSession session){
        DevMap rslt = new DevMap();
        session.setAttribute("cart", "");
        orderSvc.payment(param);
        return  rslt;
    }

    @PostMapping("/order/order_card/addCart")
    public DevMap addCart(@RequestBody DevMap param, HttpSession session){
        DevMap rslt = new DevMap();
        session.setAttribute("cart", true);
        return  rslt;
    }
}
