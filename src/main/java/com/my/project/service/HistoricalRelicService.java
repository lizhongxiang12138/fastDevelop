package com.my.project.service;


import com.my.base.service.BaseService;
import com.my.exception.IdNumOUtOFException;
import com.my.exception.NoDataException;
import com.my.project.entity.Article;
import com.my.project.entity.HistoricalRelic;

public interface HistoricalRelicService extends BaseService<com.my.project.entity.HistoricalRelic> {

    void update(HistoricalRelic historicalRelic, String[] imgGroup) throws Exception;

    void save(HistoricalRelic historicalRelic, String[] imgGroup) throws NoDataException, IdNumOUtOFException, Exception;

}
