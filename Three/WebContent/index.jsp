<%@page import="com.heima.entity.Page"%>
<%@page import="com.heima.entity.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("tr:odd").css("background-color","#D3D3D3");
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息列表</title>
</head>
<body>
	<%
		String error = (String) request.getAttribute("error");
		if (error != null) {
			if (error.equals("adderror")) {
				out.println("增加失败");
			} else if (error.equals("noadderror")) {
				out.println("增加成功！");
			}
		}
	%>
	<table border="1px">
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>操作</th>
		</tr>
		<%
			Page p = (Page)request.getAttribute("p");
			for (Student student : p.getStudents()) {
		%>
		<tr>
			<td><a href="QueryStudentBySnoServlet?sno=<%=student.getSno()%>"><%=student.getSno()%></a></td>
			<td><%=student.getSname()%></td>
			<td><%=student.getSage()%></td>
			<td><a href="DeleteStudentServlet?sno=<%=student.getSno()%>">删除</a></td>
		</tr>
		<%
			}
		%>
	</table>
	<a href="add.jsp">新增</a><br/>
	<%
		if(p.getCurrentPage()==0) {
	%>
			<a href="QueryStudentsByPage?currentPage=<%=p.getCurrentPage()+1%>">下一页</a>
			<a href="QueryStudentsByPage?currentPage=<%=p.getTotalPage()-1%>">尾页</a>
	<%
		}else if(p.getCurrentPage()==p.getTotalPage()-1) {
	%>
			<a href="QueryStudentsByPage?currentPage=0">首页</a>
			<a href="QueryStudentsByPage?currentPage=<%=p.getCurrentPage()-1%>">上一页</a>
	<%	
		}else {
	%>
			<a href="QueryStudentsByPage?currentPage=<%=p.getCurrentPage()+1%>">下一页</a>
			<a href="QueryStudentsByPage?currentPage=<%=p.getTotalPage()-1%>">尾页</a>
			<a href="QueryStudentsByPage?currentPage=0">首页</a>
			<a href="QueryStudentsByPage?currentPage=<%=p.getCurrentPage()-1%>">上一页</a>
	<% 
		}
	%>
	
</body>
</html>