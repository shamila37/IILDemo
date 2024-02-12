package com.example.demo1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.pojo.Course;

@Repository
public class CoursesDAO {
	
	
	
	@Autowired
	private DataSource datasource;
	
	
	public List<Course> findAllCourse() {
		String mysql = "select * from abc";
		
		//For task 1
//		String mysql = "select * from samples";

		Connection connection=null;
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<Course> data= new ArrayList<Course>(0);
		
		try {
			connection=datasource.getConnection();
			stmt = connection.prepareStatement(mysql);
			rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				Course course = new Course();
				
				course.setCourseId(rs.getString("code"));
				course.setCourseName(rs.getString("course_name"));
				
				//For task 1 and task 2
//				course.setId(rs.getString("id"));
//				course.setName(rs.getString("name"));
//				course.setCourseId(rs.getString("courseId"));
//				course.setCourseName(rs.getString("courseName"));
				
				data.add(course);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if(connection!= null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}
	

	public List<Course> findCourseById(String courseid) {
		String mysql = "select * from abc where code=?";
		
		//For task 1
//		String mysql = "select * from samples where name=?";
		
		//For task 2
//		String mysql = "select * from samples where courseName=?";
		
		Connection connection=null;
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<Course> data= new ArrayList<Course>(0);
		
		try {
			connection=datasource.getConnection();
			stmt = connection.prepareStatement(mysql);
			stmt.setString(1, courseid);
			
			rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				Course course = new Course();
				
				course.setCourseId(rs.getString("code"));
				course.setCourseName(rs.getString("course_name"));
				
				
				//For Task 1 and Task2
//				course.setId(rs.getString("id"));
//				course.setName(rs.getString("name"));
//				course.setCourseId(rs.getString("courseId"));
//				course.setCourseName(rs.getString("courseName"));
				
				data.add(course);
			}
			
			System.out.println(data);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if(connection!= null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}
}
