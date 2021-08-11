package com.card.shuttle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/card")
@Controller
public class CardViewCtl {

    @Autowired
    CommonCtl commonCtl;

    @GetMapping("/main")
    public String cardMain(HttpSession session, HttpServletRequest request){
        session.setAttribute("lang", commonCtl.langSet(request));
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
