package com.example.demo1.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.dao.EligibilityDao;
import com.example.pojo.Course;
import com.example.pojo.EligibilityProcessRequest;
import com.example.pojo.Person;

@RestController
public class EligibilityController {
	public static Logger nceLogger = org.slf4j.LoggerFactory.getLogger("ncoeLog");
	public static Logger nceErrorLogger = org.slf4j.LoggerFactory.getLogger("ncoeErrorLogger");
	
	@Autowired
	private EligibilityDao eligibilityDao;
	
	@PostMapping(path = "/processEligibility")
	public String processEligibility(@RequestBody EligibilityProcessRequest eligibilityProcessRequest) {
		nceLogger.debug("My processEligibility Method Called.... Called by " +eligibilityProcessRequest.getRequestedUser());
		return eligibilityDao.processEligibility(eligibilityProcessRequest);
	}

	
	@GetMapping(value="/getAllCourses")
	public List<Course> findAllCourse() {
		List<Course> data = eligibilityDao.getAllCourse();
		return data;
	}
	
}
