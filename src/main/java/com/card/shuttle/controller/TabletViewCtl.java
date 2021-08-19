package com.card.shuttle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/tablet")
@Controller
public class TabletViewCtl {

    @Autowired
    CommonCtl commonCtl;

    @RequestMapping("/main")
    public String main(HttpSession session, HttpServletRequest request){
        session.setAttribute("lang", commonCtl.langSet(request));
        System.out.println(commonCtl.langSet(request));
        return "page/tablet/main";
    }

    @RequestMapping("/registCard")
    public String tableView(){
        return "page/tablet/registCard";
    }

    @RequestMapping("/list")
    public String list(){
        return "page/tablet/list";
    }
}
