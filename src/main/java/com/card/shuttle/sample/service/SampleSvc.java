package com.card.shuttle.sample.service;

import com.card.shuttle.common.dao.CmmnDao;
import com.card.shuttle.common.exception.DevException;
import com.card.shuttle.common.module.mybatis.paginator.domain.PageBounds;
import com.card.shuttle.common.module.mybatis.paginator.domain.PageList;
import com.card.shuttle.common.module.util.DevMap;
import com.card.shuttle.common.module.util.uuid.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleSvc {

    @Autowired
    CmmnDao cmmnDao;


    public List<DevMap> ajaxSelectTest(DevMap param){
        return cmmnDao.selectList("shuttle.sample.ajaxSelectTest01", param);
    }

    public PageList<DevMap> ajaxSelectTest02(DevMap param){
        int page = Integer.parseInt(param.getString("page"));
        int pageSize = 10;

        PageBounds pageBounds = new PageBounds(page, pageSize);

        return cmmnDao.selectListPage("shuttle.sample.ajaxSelectTest01", param, pageBounds);
    }

    public List<DevMap> exceptionTest(DevMap param) throws  DevException {
        throw new DevException("exceptionTest");
    }

    public List<DevMap> selectData(){
        DevMap param = new DevMap() {{
            put("USR_NAME", "홍길동");
        }};
        return cmmnDao.selectList("shuttle.sample.ajaxSelectTest01", param);
    }

    public void resetData(DevMap param){
        cmmnDao.delete("shuttle.sample.deleteResetData");
    }
    
    public void insertTest(){
        DevMap param = new DevMap(){{
            put("id", UuidUtil.getUuidOnlyString());
            put("usrId", "USR_TEST_01");
            put("usrName", "홍길동01");
            put("usrNo", 11);
            
        }};
        cmmnDao.insert("shuttle.sample.insertTest", param);
    }

    public void updateTest(DevMap param){
        param.put("changeName", "홍길동변경");
        cmmnDao.update("shuttle.sample.updateTest", param);
    }
}
