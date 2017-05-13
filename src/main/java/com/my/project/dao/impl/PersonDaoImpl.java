package com.my.project.dao.impl;

import com.my.base.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

@Repository(value="personDao")
public class PersonDaoImpl extends BaseDaoImpl<com.my.project.entity.Person> implements com.my.project.dao.PersonDao {

}
