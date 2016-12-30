package com.my.project.dao.impl;

import com.my.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository(value="historicalRelicDao")
public class HistoricalRelicDaoImpl extends BaseDaoImpl<com.my.project.entity.HistoricalRelic> implements com.my.project.dao.HistoricalRelicDao {

}
