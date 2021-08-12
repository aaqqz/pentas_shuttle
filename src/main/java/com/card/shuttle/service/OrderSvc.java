package com.card.shuttle.service;

import com.card.shuttle.common.dao.CmmnDao;
import com.card.shuttle.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderSvc {

    @Autowired
    CmmnDao cmmnDao;

    public void payment(DevMap param){

        cmmnDao.update("shuttle.order.cardPayment", param);
        //cmmnDao.insert("shuttle.order.cardUse", param);
    }

}
