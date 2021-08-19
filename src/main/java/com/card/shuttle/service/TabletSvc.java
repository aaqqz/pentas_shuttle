package com.card.shuttle.service;

import com.card.shuttle.common.dao.CmmnDao;
import com.card.shuttle.common.module.util.DevMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabletSvc {

    @Autowired
    CmmnDao cmmnDao;

    public List<DevMap> getCashierInfo(DevMap param){
        return cmmnDao.selectList("shuttle.card.getCashierInfo", param);
    }

}
