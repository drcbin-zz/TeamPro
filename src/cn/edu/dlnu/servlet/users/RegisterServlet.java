package cn.edu.dlnu.servlet.users;

import java.io.IOException;
import java.nio.file.attribute.DosFileAttributes;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.alibaba.fastjson.JSONObject;

import cn.edu.dlnu.daos.UserMapper;
import cn.edu.dlnu.models.*;

import cn.edu.dlnu.test.MainTest;
import cn.edu.dlnu.tools.ErrorCode;
import cn.edu.dlnu.tools.ResponseJsonItems;
import cn.edu.dlnu.tools.Status;

public class RegisterServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		super.service(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		SqlSessionFactoryBuilder sb = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = sb.build(MainTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));
		SqlSession session = factory.openSession();	

		UserMapper mapper = session.getMapper(UserMapper.class);
		JSONObject resultJson = new JSONObject();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		
		if(username == null || password == null || email == null){
			resultJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
			resultJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1012);
			resultJson.put(ResponseJsonItems.MSG, "信息填写不全");
			resp.getWriter().write(resultJson.toString());
			return;	
		}

			
		// 
		User userModel = new User();	
		userModel.setNickname(username);
		userModel.setPassword(password);
		userModel.setEmail(email);
		userModel.setCdate(new Date(System.currentTimeMillis()));
		userModel.setStatus(Status.getStatusCode(Status.ACTIVE));

		// 鎻掑叆鏁版嵁搴�
		int flag = mapper.insert(userModel);
		session.commit();
		
		
		if(flag == 0)			
			resultJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_FALSE);
			resultJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1040);
			resultJson.put(ResponseJsonItems.MSG, "服务器位置错误");{
		}

		resultJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_SUCCESS);
		session.close();
		resp.getWriter().write(resultJson.toString());

	}  // end doPost

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		JSONObject resultJson = new JSONObject();
		String username = req.getParameter("username");
		
		
		// check username is right
		if(username == null || username.length() < 5){
			resultJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
			resultJson.put(ResponseJsonItems.MSG, "名字长度不够,5位以上");
			resp.getWriter().write(resultJson.toString());
			return;
		}
		
		
		// check username is exist
		SqlSessionFactoryBuilder sb = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = sb.build(MainTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));
		SqlSession session = factory.openSession();	
		UserMapper mapper = session.getMapper(UserMapper.class);	
		User user = mapper.selectByUsername(username);
		if (user == null){
			resultJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_TRUE);
		}else{
			resultJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_FALSE);
		}
		resp.getWriter().write(resultJson.toString());

	}
}
