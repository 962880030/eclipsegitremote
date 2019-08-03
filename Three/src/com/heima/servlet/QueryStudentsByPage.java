package com.heima.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.entity.Page;
import com.heima.entity.Student;
import com.heima.service.IStudentService;
import com.heima.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class QueryStudentsByPage
 */
public class QueryStudentsByPage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IStudentService service = new StudentServiceImpl();
		int count = service.getTotalCount();
		Page page = new Page();
		String cPage = request.getParameter("currentPage");
		if(cPage == null) {
			cPage = "0";
		}
		int currentPage = Integer.parseInt(cPage);
		page.setCurrentPage(currentPage);
		int totalCount = service.getTotalCount();
		page.setTotalCount(totalCount);
		int pageSize = 3;
		page.setPageSize(pageSize);
		List<Student> students = service.queryStudentsByPage(currentPage, pageSize);
		System.out.println(students);
		System.out.println(count);
		page.setStudents(students);
		request.setAttribute("p", page);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
