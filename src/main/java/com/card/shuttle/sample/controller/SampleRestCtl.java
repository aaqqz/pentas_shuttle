package com.card.shuttle.sample.controller;

import com.card.shuttle.common.exception.DevException;
import com.card.shuttle.common.module.mybatis.paginator.domain.PageList;
import com.card.shuttle.common.module.util.DevMap;
import com.card.shuttle.sample.service.SampleSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SampleRestCtl {

    @Autowired
    SampleSvc sampleSvc;

    @PostMapping("/sample/ajaxSelectTest/test01")
    public List<DevMap> ajaxSelectTest01(@RequestBody DevMap param){
        return sampleSvc.ajaxSelectTest(param);
    }

    @PostMapping("/sample/ajaxSelectTest/test02")
    public DevMap ajaxSelectTest02(@RequestBody DevMap param){
        DevMap rslt = new DevMap();

        PageList<DevMap> listPage = sampleSvc.ajaxSelectTest02(param);

        rslt.put("listPage", listPage);
        rslt.put("pageInfo", listPage.getPaginator());

        return rslt;
    }

    @PostMapping("/sample/exceptionTest/test01")
    public DevMap exceptionTest(@RequestBody DevMap param) throws DevException {
        DevMap rslt = new DevMap();

        List<DevMap> rsltList = sampleSvc.exceptionTest(param);
        return rslt;
    }

    @PostMapping("/sample/transactionTest/selectData")
    public List<DevMap> selectData(@RequestBody DevMap param){
        return sampleSvc.selectData();
    }

    @PostMapping("/sample/crudTest/resetData")
    public DevMap resetData(@RequestBody DevMap param){
        sampleSvc.resetData(param);

        DevMap rslt = new DevMap();
        rslt.put("rsltSatus", "SUCC");
        return rslt;
    }

    @PostMapping("/sample/crudTest/insertTest")
    public DevMap insertTest(@RequestBody DevMap param){
        sampleSvc.insertTest();

        DevMap rslt = new DevMap();
        rslt.put("rsltSatus", "SUCC");
        return rslt;
    }


    @PostMapping("/sample/crudTest/updateTest")
    public List<DevMap> updateTest(@RequestBody DevMap param){
        sampleSvc.updateTest(param);

        return selectData(param);
    }

    @PostMapping("/sample/ajaxParallelTest/run_ajax1")
    @ResponseBody
    public DevMap run_ajax1(@RequestBody DevMap param) throws InterruptedException {

        Thread.sleep(2000);

        param.put("rslt", "SUCC1");

        return param;
    }

    @PostMapping("/sample/ajaxParallelTest/run_ajax2")
    @ResponseBody
    public DevMap run_ajax2(@RequestBody DevMap param) throws InterruptedException {

        Thread.sleep(1000);

        param.put("rslt", "SUCC2");

        return param;
    }

    @RequestMapping("/sample/ajaxParallelTest/run_ajax3")
    @ResponseBody
    public DevMap run_ajax3(@RequestBody DevMap params) throws InterruptedException {

        if("111".equals(params.getString("idx"))) {
            Thread.sleep(2000);
            params.put("rslt", "SUCC111");
        } else if("222".equals(params.getString("idx"))) {
            Thread.sleep(1000);
            params.put("rslt", "SUCC222");
        }

        return params;
    }
}
