package com.heima.service;

import java.util.List;

import com.heima.entity.Student;

public interface IStudentService {
	public List<Student> queryAllStudents(); 
		
public Student queryStudentBySno(int sno);

public boolean updateStudentBySno(int sno,Student student);

public boolean deleteStudentBySno(int sno);

public boolean addStudent(Student student);

public int getTotalCount();

public List<Student> queryStudentsByPage(int currentPage,int pageSize);
}
