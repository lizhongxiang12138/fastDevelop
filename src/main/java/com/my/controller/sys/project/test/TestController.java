package com.my.controller.sys.project.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.controller.sys.project.test.model.TestModel;
import com.my.project.entity.AuthUser;

@Controller
@RequestMapping(value="/sys/project/test")
public class TestController {

    @RequestMapping(value="/test")
    public @ResponseBody Map<String, Object> test( @RequestBody TestModel testModel ){
	Map<String, Object> result = new HashMap<String, Object>();
	for (AuthUser a : testModel.getUsers()) {
	    System.out.println(a);
	}
	
	return result;
    }
}
