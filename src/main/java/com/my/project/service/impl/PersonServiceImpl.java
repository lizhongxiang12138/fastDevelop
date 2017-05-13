package com.my.project.service.impl;

import org.springframework.stereotype.Service;
import com.my.base.service.impl.BaseServiceImpl;


@Service("personService")
public class PersonServiceImpl extends BaseServiceImpl<com.my.project.entity.Person> implements com.my.project.service.PersonService {

}
