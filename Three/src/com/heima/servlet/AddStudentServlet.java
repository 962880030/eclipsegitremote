package com.heima.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heima.entity.Student;
import com.heima.service.IStudentService;
import com.heima.service.impl.StudentServiceImpl;

public class AddStudentServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/html;charset=utf-8");//GET编码
		request.setCharacterEncoding("utf-8");//POST编码
		int no = Integer.parseInt(request.getParameter("sno"));
		String name = request.getParameter("sname");
		int age = Integer.parseInt(request.getParameter("sage"));
		String address = request.getParameter("saddress");
		Student student = new Student(no,name,age,address);
		IStudentService studentService = new StudentServiceImpl();
		boolean result = studentService.addStudent(student);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		/*if(result) {
			//out.println("增加成功！");
			response.sendRedirect("QueryAllStudentsServlet");
		}else {
			out.println("增加失败");
		}*/
		if(!result) {
			request.setAttribute("error", "adderror");
		}else {
			request.setAttribute("error", "noadderror");
		}
		request.getRequestDispatcher("QueryAllStudentsServlet").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
