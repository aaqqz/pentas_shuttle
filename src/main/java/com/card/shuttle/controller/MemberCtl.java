package com.card.shuttle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
@Controller
public class MemberCtl {

    @GetMapping("/signUp")
    public String signUp(){
        return "page/member/signUp";
    }
}
