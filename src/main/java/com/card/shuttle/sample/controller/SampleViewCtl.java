package com.card.shuttle.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sample")
@Controller
public class SampleViewCtl {


    @GetMapping({"/", ""})
    public String openIndex(){
        return "sample/index";
    }

    @GetMapping("/layoutTest")
    public String sampleLayout(){
        return "/sample/defaultLayoutTest";
    }

    @GetMapping("/language1")
    public String languageTest1(){
        return "/sample/languageTest1";
    }
    
    @GetMapping("/language2")
    public String languageTest2(){
        return "/sample/languageTest2";
    }

    @GetMapping("/ajaxSelectTest")
    public String ajaxSelectTest(){ return "sample/ajaxSelectTest"; }

    @GetMapping("/scriptTest")
    public String scriptTest(){
        return "sample/scriptTest";
    }

    @GetMapping("/exceptionTest")
    public String exceptionTest(){
        return "ajaxParallelTest";
    }

    @GetMapping("/crudTest")
    public String crudTest(){
        return "sample/crudTest";
    }

    @GetMapping("/ajaxParallelTest")
    public String ajaxParallelTest(){
        return "sample/ajaxParallelTest";
    }

    @GetMapping("/androidTest")
    public String androidTest(){
        return "sample/androidTest";
    }
}
