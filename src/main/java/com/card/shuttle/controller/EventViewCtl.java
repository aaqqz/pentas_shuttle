package com.card.shuttle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/event")
@Controller
public class EventViewCtl {

    @GetMapping("/list")
    public String eventList(){
        return "page/event/list";
    }

    @GetMapping("/detail")
    public String evenDetail(){
        return "page/event/detail";
    }
}
