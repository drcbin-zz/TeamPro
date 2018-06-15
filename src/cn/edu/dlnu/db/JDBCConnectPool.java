package cn.edu.dlnu.db;

import java.awt.List;
import java.sql.*;
import java.util.ArrayList;

import org.apache.catalina.User;

import cn.edu.dlnu.models.*;
public class JDBCConnectPool {
	
	// 鍒濆鍖栧彉閲�
	final static int maxConnections = 10;
	private List free = new List();
	private List busy = new List();
	private Connection connection = null;
	private static String url = "jdbc:mysql://192.168.13.100:3306/teamtest?characterEncoding=utf8";
	private static String username = "root";
	private static String password = "root";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		// 杩欓噷璐熻矗杩斿洖connection
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			this.connection = DriverManager.getConnection(url, username, password);
//			String sql = "select * from userinfo where username=?";
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, "chenbin");
//			sql = preparedStatement.toString();
//			System.out.println(sql);
//			ResultSet resultSet =  preparedStatement.executeQuery();
//			
//			ArrayList<Model> models = ModelsFactory.createModels(UserModel.MODEL_NAME_STR, resultSet);
//			System.out.println("success");
			return  connection != null ? connection : DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	static public void close(Connection connection) {
		// 鍏抽棴鎸囧畾鐨刢onnection锛� 瀵逛簬杩炴帴姹�,鍒欑洿鎺ユ斁杩涙寚瀹氬鍣�
		if (connection == null) {
			return;
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
