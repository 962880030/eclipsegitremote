package com.heima.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.entity.Student;
import com.heima.service.IStudentService;
import com.heima.service.impl.StudentServiceImpl;

import sun.print.resources.serviceui;

/**
 * Servlet implementation class UpdateStudentServlet
 */
public class UpdateStudentServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int no = Integer.parseInt(request.getParameter("sno"));
		String name = request.getParameter("sname");
		int age = Integer.parseInt(request.getParameter("sage"));
		String address = request.getParameter("saddress");
		Student student = new Student(name,age,address);
		IStudentService service = new StudentServiceImpl();
		boolean result = service.updateStudentBySno(no, student);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		if(result) {
			//response.getWriter().println("修改成功！");
			response.sendRedirect("QueryAllStudentsServlet");
			//request.getRequestDispatcher("QueryAllStudentsServlet").forward(request, response);
		}else {
			response.getWriter().println("修改失败");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
