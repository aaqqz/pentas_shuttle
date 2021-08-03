package com.card.shuttle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/")
@Controller
public class CommonCtl {

    @Autowired
    LocaleResolver localeResolver;

    @GetMapping({"/", ""})
    public String openPage(){
        return "redirect:/card/main";
    }

    public String langSet(HttpServletRequest request){
        return localeResolver.resolveLocale(request).toString();
    }
}