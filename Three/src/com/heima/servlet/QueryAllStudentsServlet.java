package com.heima.servlet;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.entity.Student;
import com.heima.service.IStudentService;
import com.heima.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class QueryAllStudentsServlet
 */
public class QueryAllStudentsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		IStudentService service = new StudentServiceImpl();
		List<Student> students = service.queryAllStudents();
		System.out.println(students);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		request.setAttribute("students", students);//pageContext<request<session<application
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
