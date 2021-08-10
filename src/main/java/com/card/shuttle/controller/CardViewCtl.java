package com.card.shuttle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/card")
@Controller
public class CardViewCtl {

    @GetMapping("/main")
    public String cardMain(){
        return "/page/card/main";
    }

    @GetMapping("/history")
    public String cardHistory(){
        return "/page/card/history";
    }

    @GetMapping("/register")
    public String cardRegister(){
        return "/page/card/register";
    }
    @GetMapping("/change")
    public String cardChange(){
        return "/page/card/change";
    }
    @GetMapping("/change_sw")
    public String changeSw(){
        return "/page/card/change_sw";
    }
}
