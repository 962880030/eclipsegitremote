package com.heima.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.entity.Student;
import com.heima.service.IStudentService;
import com.heima.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class QueryStudentBySnoServlet
 */
public class QueryStudentBySnoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int no = Integer.parseInt(request.getParameter("sno"));
		IStudentService service = new StudentServiceImpl();
		Student student = service.queryStudentBySno(no);
		System.out.println(student);
		request.setAttribute("student", student);
		request.getRequestDispatcher("studentInfo.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
