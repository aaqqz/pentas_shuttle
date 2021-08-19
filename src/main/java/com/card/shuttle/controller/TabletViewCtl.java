package com.card.shuttle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tablet")
@Controller
public class TabletViewCtl {

    @RequestMapping("registCard")
    public String tableView(){
        return "page/tablet/registCard";
    }
}
