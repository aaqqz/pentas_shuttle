package com.card.shuttle.controller;

import com.card.shuttle.common.module.util.DevMap;
import com.card.shuttle.service.TabletSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class TabletRestCtl {

    @Autowired
    TabletSvc tabletSvc;

    @Autowired
    CommonCtl commonCtl;

    @PostMapping("/tablet/main/loginProc")
    public DevMap loginProc(@RequestBody DevMap param, HttpSession session){
        DevMap rslt = new DevMap();
        System.out.println(param.get("cashierId"));
        session.setAttribute("cashierId", param.get("cashierId"));
        System.out.println(session.getAttribute("cashierId"));
        rslt.put("rsltData", session.getAttribute("cashierId"));

        return rslt;
    }

    @PostMapping("/tablet/list/getCashierInfo")
    public DevMap getCashierInfo(@RequestBody DevMap param) {
        DevMap rslt = new DevMap();
        List<DevMap> rsltList = tabletSvc.getCashierInfo(param);

        rslt.put("CashierList", rsltList);
        return rslt;
    }
}
