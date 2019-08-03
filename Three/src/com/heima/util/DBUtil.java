package com.heima.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.heima.entity.Student;

public class DBUtil {
	private static final String url = "jdbc:mysql://localhost:3306/stus?useUnicode=true&characterEncoding=utf8";
	private static final String username = "root";
	private static final String password = "root";
	public static Connection connection = null;
	public static PreparedStatement pstmt = null;
	public static ResultSet rs = null;
	public static int getTotalCount(String sql) {
		int count = -1;
		try {
			pstmt = createPreparedStatement(sql, null);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, connection);
		}
		return count;
	}
	public static boolean executeUpdate(String sql, Object[] params) {
		try {
			// String sql = "delete from s_stu where sno =?";
			// pstmt.setInt(1, sno);
			pstmt = createPreparedStatement(sql, params);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeAll(null,pstmt,connection);
		}
	}
	public static void closeAll(ResultSet rs,Statement stmt,Connection connection) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url,username,password);
	}
	public static PreparedStatement createPreparedStatement(String sql,Object[] params) throws ClassNotFoundException, SQLException {
		pstmt = getConnection().prepareStatement(sql);
		if(params!=null) {
		for(int i = 0 ; i < params.length; i++) {
			pstmt.setObject(i+1, params[i]);
			}
		}
		return pstmt;
	}
	public static ResultSet executeQuery(String sql,Object[] params) {
		List<Student> students = new ArrayList<>();
		Student student = null;
		try {
			//String sql = "select * from s_stu";
			pstmt = createPreparedStatement(sql,params);
			rs = pstmt.executeQuery();
			return rs;
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
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
			//if(connection!=null) connection.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
