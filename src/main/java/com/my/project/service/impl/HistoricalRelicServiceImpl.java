package com.my.project.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.my.base.service.impl.BaseServiceImpl;
import com.my.exception.IdNumOUtOFException;
import com.my.exception.NoDataException;
import com.my.project.dao.DocumentDao;
import com.my.project.entity.Article;
import com.my.project.entity.Document;
import com.my.project.entity.HistoricalRelic;


@Service("historicalRelicService")
public class HistoricalRelicServiceImpl extends BaseServiceImpl<com.my.project.entity.HistoricalRelic> implements com.my.project.service.HistoricalRelicService {

    @Resource
    private DocumentDao documentDao;
    
    @Override
    public void update(HistoricalRelic historicalRelic, String[] imgGroup) throws Exception {
	//更新文章
	super.update(historicalRelic);
	updateImgGroup(historicalRelic, imgGroup);
    }

    @Override
    public void save(HistoricalRelic historicalRelic, String[] imgGroup) throws NoDataException, IdNumOUtOFException,Exception{
	if (historicalRelic.getId()!=null) {
	    historicalRelic.setId(super.getGenerateId("ARTICLE"));
	}
	super.save(historicalRelic);
	updateImgGroup(historicalRelic, imgGroup);
	
    }
    
    /**
     * 更新图片组
     * @param a
     * @param imgGroup
     * @throws Exception
     */
    private void updateImgGroup(HistoricalRelic historicalRelic,String[] imgGroup) throws Exception {
	if (imgGroup != null&&imgGroup.length>0) {
		for (String s : imgGroup) {
		    Document d = documentDao.findById(s);
		    if(d==null) {
			continue;
		    }
		    d.setServiceID(historicalRelic.getId());
		    d.setServiceName(historicalRelic.getTitle()+"的图片组");
		    documentDao.update(d);
		}
	}
    }
}
