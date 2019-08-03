package com.heima.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.heima.dao.IStudentDao;
import com.heima.entity.Student;
import com.heima.util.DBUtil;

public class StudentDaoImpl implements IStudentDao{
	private final String url = "jdbc:mysql://localhost:3306/stus?useUnicode=true&characterEncoding=utf8";
	private final String username = "root";
	private final String password = "root";
	public boolean addStudent(Student student) {
		String sql = "insert into s_stu values(?,?,?,?)";
		Object[] params = {student.getSno(),student.getSname(),student.getSage(),student.getSaddress()};
		return DBUtil.executeUpdate(sql, params);
	}
	public boolean updateStudentBySno(int sno,Student student) {
		String sql = "update s_stu set sname = ?, sage = ? , saddress = ? where sno = ?";
		Object[] params = {student.getSname(),student.getSage(),student.getSaddress(),sno};
		return DBUtil.executeUpdate(sql, params);
	}
	public boolean deleteStudentBySno(int sno) {
		String sql = "delete from s_stu where sno =?";
		Object[] params = {sno};
		return DBUtil.executeUpdate(sql, params);
	}
	public List<Student> queryAllStudents() {
		PreparedStatement pstmt = null;
		Student student = null;
		List<Student> students = new ArrayList<>();
		ResultSet rs = null;
		try {
			String sql = "select * from s_stu";
			rs = DBUtil.executeQuery(sql, null);
			//rs = pstmt.executeQuery();
			while(rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				student = new Student(no,name,age,address);
				students.add(student);
			}
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeAll(rs, pstmt, DBUtil.connection);
		/*	try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(DBUtil.connection!=null) DBUtil.connection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}*/
		}
	}
	public boolean isExist(int sno) {
		return queryStudentBySno(sno) == null? false : true;
	}
	public Student queryStudentBySno(int sno) {
		Student student = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url,username,password);
			String sql = "select * from s_stu where sno = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, sno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				student = new Student(no,name,age,address);
			}
			return student;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(connection!=null) connection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public int getTotalCount() {
		String sql = "select count(*) from s_stu";
		return DBUtil.getTotalCount(sql);
	}
	public List<Student> queryStudentsByPage(int currentPage,int pageSize) {
		String sql = "select * from s_stu limit ?,?";
		Object[] params = {currentPage*pageSize,pageSize};
		List<Student> students = new ArrayList<>();
		ResultSet rs = DBUtil.executeQuery(sql, params);
		try {
			while(rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				Student student = new Student(no,name,age,address);
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}
}
