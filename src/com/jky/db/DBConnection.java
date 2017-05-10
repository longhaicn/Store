package com.jky.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

	private static final String DRIVE = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/store?useUnicode=true&characterEncoding=UTF-8";
	private static final String USERNAME = "root"; 
	private static final String USERPASSWD = ""; 
	protected static Connection conn;
	//4.连接数据库的准备
	protected static PreparedStatement pstmt;
	//5.结果集
	protected static ResultSet rs;
	public static Connection getConnection() {
		try {
			Class.forName(DRIVE);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败！");
		}
		try {

			conn = DriverManager.getConnection(URL, USERNAME, USERPASSWD);
			System.out.println("数据库连接成功！");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败！");
		}
		return conn;
	}
	//登陆 user1 11111  me
	//select * from member where username='user1' and password='111111';
	//一张表对应一个实体
	public void closeAll(){
		
		try {
			if(rs!=null){
				rs.close();
				rs = null;
			}
			if(pstmt!=null){
				pstmt.close();
				pstmt = null;
			}
			if(conn!=null){
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}





