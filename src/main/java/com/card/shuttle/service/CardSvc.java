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
        List<DevMap> rsltList = cmmnDao.selectList("shuttle.card.getCardList", param);

        for (DevMap devMap : rsltList) {
            System.out.println(devMap.get("cardNo"));
            devMap.put("hisList", cmmnDao.selectList("shuttle.card.getCardHisList", devMap.get("cardNo")));
        }

        return rsltList;
    }

    public List<DevMap> getHisInfo(DevMap param){
        return cmmnDao.selectList("shuttle.card.getHisInfo", param);
    }

    public void cardRegist(DevMap param){
        cmmnDao.update("shuttle.card.cardRegist", param);
    }

    public DevMap cardRegistCheck(DevMap param){
        return cmmnDao.selectOne("shuttle.card.cardRegistCheck", param);
    }

}
