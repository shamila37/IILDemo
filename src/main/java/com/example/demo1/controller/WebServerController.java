package com.example.demo1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.dao.CoursesDAO;
import com.example.pojo.Course;
//import com.example.demo1.dao.StudentDAO;
import com.example.pojo.Person;

@RestController
public class WebServerController {
	
//	@Autowired
//	private StudentDAO studentDAO;
	
	@Autowired
	private CoursesDAO courseDAO;
	
	@GetMapping(value ="/sayHello")
	public String sayHello() {
		return "Hello Web Service Called..";
	}
	
//	@GetMapping(value="/calculatorSum")
//	public int calculatorSum(int x, int y) {
//		try {
//			return x + y;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}
//	
//	public static void main(String [] args) {
//		WebServerController webServerController = new WebServerController();
//		int sum = webServerController.calculatorSum(5, 14);
//		System.out.println("Sum is " + sum);
//	}
	
//	@GetMapping(value = "/getVegetableInformation/{vegetableName}/{color}")
//	public String getVegetableInformation(@PathVariable String vegetableName, @PathVariable String color) {
//		return "Today I have 20Kg " + color + " " + vegetableName;
//	}
//	
//	
//	@PostMapping(path = "/savePersonInformation")
//	public void savePersonInformation(@RequestBody Person personobj) {
//		System.out.println(" Person Surname is " + personobj.getSurname());
//		System.out.println(" Person Othername is " + personobj.getOthername());
//		System.out.println(" Person Age is " + personobj.getAge());
//		System.out.println(" Person Address is " + personobj.getAddress());
//	}
//	
	@GetMapping(value="/findAllCourse")
	public List<Course> findAllCourse() {
		List<Course> data = courseDAO.findAllCourse();
		return data;
	}
	
	@GetMapping(value="/findCourseById/{courseid}")
	public List<Course> findCourseById(@PathVariable String courseid) {
		
		System.out.println(courseid);
		
		List<Course> data = courseDAO.findCourseById(courseid);
		
		return data;
	}
	

}


