package com.card.shuttle.common.exception;

import com.card.shuttle.common.module.util.CmmnUtil;
import com.card.shuttle.common.module.util.DevMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice  
@RestController
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DevException.class)  
    public DevMap handleDevException(DevException e){
    	log.error("DevException ====> ");
    	log.error(CmmnUtil.getExceptionMsg(e));
    	
        return new DevMap() {{put("rsltStat", "dev-error"); put("errMsg", e.getMessage());}};
    }
    
    @ExceptionHandler(value = Exception.class)  
    public DevMap handleException(Exception e){
    	log.error("Exception ====> ");
    	log.error(CmmnUtil.getExceptionMsg(e));
    	return new DevMap() {{put("rsltStat", "error");}};
    }
}
