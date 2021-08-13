package com.card.shuttle.controller;

import com.card.shuttle.common.module.util.DevMap;
import com.card.shuttle.service.CardSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CardRestCtl {

    @Autowired
    CardSvc cardSvc;

    @Autowired
    CommonCtl commonCtl;

    @PostMapping("/card/main/loginProc")
    public DevMap loginProc(@RequestBody DevMap param, HttpSession session){
        DevMap rslt = new DevMap();
        session.setAttribute("userId", "shuttle@shuttle.com");
        rslt.put("rsltData", "succ");

        return rslt;
    }

    @PostMapping("/card/main/getCardList")
    public DevMap getCardLIst(@RequestBody DevMap param){
        DevMap rslt = new DevMap();
        List<DevMap> rsltList = cardSvc.getCardList(param);

        rslt.put("cardList", rsltList);
        return rslt;
    }

    @PostMapping("/card/history/getHisInfo")
    public DevMap getHisInfo(@RequestBody DevMap param){
        DevMap rslt = new DevMap();
        List<DevMap> cardHisList = cardSvc.getHisInfo(param);

        rslt.put("cardHisList", cardHisList);
        return rslt;
    }












    @PostMapping("/card/main/logout")
    public DevMap logout(@RequestBody DevMap param, HttpSession session){
        DevMap rslt = new DevMap();
        session.setAttribute("userId", null);
        rslt.put("rsltData", "succ");
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
