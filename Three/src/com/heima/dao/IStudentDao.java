package com.heima.dao;

import java.util.List;

import com.heima.entity.Student;

public interface IStudentDao {
	public boolean addStudent(Student student);
	
	public boolean deleteStudentBySno(int sno);
	
	public boolean updateStudentBySno(int sno, Student student);
	
	public int getTotalCount();
	
	public List<Student> queryStudentsByPage(int currentPage,int pageSize);
	
	public boolean isExist(int sno);
	
	public List<Student> queryAllStudents();

	public Student queryStudentBySno(int sno);
}