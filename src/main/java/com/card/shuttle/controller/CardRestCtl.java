package com.card.shuttle.controller;

import com.card.shuttle.common.module.util.DevMap;
import com.card.shuttle.service.CardSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class CardRestCtl {

    @Autowired
    CardSvc cardSvc;

    @PostMapping("/card/main/loginProc")
    public DevMap loginProc(@RequestBody DevMap param, HttpSession session){
        DevMap rslt = new DevMap();
        session.setAttribute("loginStatus", "LoginSucc");

        rslt.put("succ", "succ");

        return rslt;
    }

    @PostMapping("/card/main/sessionCheck")
    public DevMap sessionCheck(@RequestBody DevMap param, HttpSession session){
        DevMap rslt = new DevMap();
        String loginStatus = session.getAttribute("loginStatus").toString();
        rslt.put("loginStatus", loginStatus);

        return rslt;
    }
}
