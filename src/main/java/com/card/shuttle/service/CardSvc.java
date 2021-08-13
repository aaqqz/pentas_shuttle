package com.card.shuttle.service;

import com.card.shuttle.common.dao.CmmnDao;
import com.card.shuttle.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardSvc {

    @Autowired
    CmmnDao cmmnDao;

    public List<DevMap> test(DevMap param){
        return cmmnDao.selectList("shuttle.sample.test", param);
    }

    public List<DevMap> getCardList(DevMap param){
        return cmmnDao.selectList("shuttle.card.getCardList", param);
    }

    public List<DevMap> getHisInfo(DevMap param){
        return cmmnDao.selectList("shuttle.card.getHisInfo", param);
    }
}
