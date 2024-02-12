package com.example.demo1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.pojo.Course;
import com.example.pojo.EligibilityProcessRequest;

@Repository
public class EligibilityDao {
	
	public static Logger ncelogger = org.slf4j.LoggerFactory.getLogger("ncoeLog");
	public static Logger nceErrorlogger = org.slf4j.LoggerFactory.getLogger("ncoeErrorLog");
	
	@Autowired
	private DataSource datasource;
	
	public String processEligibility(EligibilityProcessRequest eligibilityProcessRequest) {
		
	    String sql="";
	   	PreparedStatement stmt=null;
		ResultSet rs=null;
		Connection conn=null;
		List<Course> allCourses= new ArrayList<Course>();
		try {
			ncelogger.debug("Our EligibilityDao processEligibility Method Called...");
			ncelogger.debug("Try to Create Database Connection");
			conn=datasource.getConnection();
			if(conn!=null) {
				ncelogger.debug("Create Database Connection Successful");
			}
			
			sql="select * from edu_m_all_courses";
			stmt=conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()) {
				Course course = new Course();
				course.setCourseCode(rs.getString("code"));
				course.setCourseName(rs.getString("course_name"));
				allCourses.add(course);
			}
			stmt.close();
			rs.close();
			ncelogger.debug("Found "+allCourses.size()+" Courses");
			for(Course currentCourse:allCourses) {
				try {
					ncelogger.debug("Course "+currentCourse.getCourseName()+" Selected for Eligibility Check");
					
					if(currentCourse.getCourseCode().equalsIgnoreCase("1")) { // Primary Education
						processCourse_1(currentCourse,conn);
					}
					
				}catch(Exception e) {
					nceErrorlogger.error("Unable to Process Course "+currentCourse.getCourseName());
				}
			}
			
		}catch(Exception e) {
			nceErrorlogger.error(e.getMessage());
			return "Error Occurred";
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "Success";
	}
	
	public void processCourse_1(Course primaryEducationCourse,Connection conn) throws SQLException {
		PreparedStatement stmt=null;
		ResultSet rs=null;
			try {
				String sql="select * from edu_t_application "
						+ "where course_pref_one=? or course_pref_two=? or course_pref_three=?";
				
				stmt=conn.prepareStatement(sql);
				rs=stmt.executeQuery();
				while(rs.next()) {
					String applicationNo=null;
					try {
						applicationNo=rs.getString("application_no");
						
					}catch(Exception e) {
						nceErrorlogger.error("Unable to Process Student With Application No "+applicationNo);
					}
				}
			}catch(Exception e) {
				throw e;
			}finally {
				if(rs!=null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(stmt!=null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	}
	
	public List<Course> getAllCourse() {
		String mysql = "select * from edu_m_all_courses";
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<Course> data= new ArrayList<Course>(0);
		
		Connection connection = null;
		try {
			connection=datasource.getConnection();
			stmt = connection.prepareStatement(mysql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Course course = new Course();
				
				course.setCourseName(rs.getString("course_name"));
				
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

}
