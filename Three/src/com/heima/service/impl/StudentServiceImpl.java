package com.heima.service.impl;

import java.util.List;

import com.heima.dao.IStudentDao;
import com.heima.dao.impl.StudentDaoImpl;
import com.heima.entity.Student;
import com.heima.service.IStudentService;

public class StudentServiceImpl implements IStudentService{
	IStudentDao dao = new StudentDaoImpl();
	public List<Student> queryAllStudents() {
			return dao.queryAllStudents();
	}
	public Student queryStudentBySno(int sno) {
			return dao.queryStudentBySno(sno);
	}
	public boolean updateStudentBySno(int sno,Student student) {
		if(dao.isExist(sno)) {
			return dao.updateStudentBySno(sno, student);
		}else {
			return false;
		}
	}
	public boolean deleteStudentBySno(int sno) {
		if(dao.isExist(sno)) {
			return dao.deleteStudentBySno(sno);
		}else {
			return false;
		}
	}
	public boolean addStudent(Student student) {
		if(!dao.isExist(student.getSno())) {
			dao.addStudent(student);
			return true;
		}else{
			System.out.println("此人已存在");
			return false;
		}
	}
	public int getTotalCount() {
		return dao.getTotalCount();
	}
	public List<Student> queryStudentsByPage(int currentPage, int pageSize) {
		return dao.queryStudentsByPage(currentPage, pageSize);
	}
}
