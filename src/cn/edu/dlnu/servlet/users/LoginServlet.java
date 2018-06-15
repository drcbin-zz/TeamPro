package cn.edu.dlnu.servlet.users;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.ibatis.session.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.edu.dlnu.daos.UserMapper;
import cn.edu.dlnu.models.User;
import cn.edu.dlnu.test.MainTest;
import cn.edu.dlnu.tools.ErrorCode;
import cn.edu.dlnu.tools.ResponseJsonItems;

//@WebServlet("/web/user/login")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		SqlSessionFactoryBuilder sb = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = sb.build(MainTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml"));
		SqlSession session = factory.openSession();	
		
		UserMapper mapper = session.getMapper(UserMapper.class);
		JSONObject resultJson = new JSONObject();
		
		String username = req.getParameter("username");
		System.out.println(username);
//		String nickname = req.getParameter(UserModel.NICK_NAME_STR);
		String password = req.getParameter("password");
		if (username == null || password == null) {
			resultJson.put(ResponseJsonItems.STATUS,  ResponseJsonItems.STATUS_ERROR);
			resultJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1012);
			resultJson.put(ResponseJsonItems.MSG,  "请输入用户名和密码");
			resp.getWriter().write(resultJson.toString());
			return;
		}

		
		//从数据库中获取数据
		User user = mapper.selectByUsername(username);
		
			// 判断用户是否存在
			if ( user !=null ) {
				if(user.getPassword().equals(password)){
//	等superdong做完
					if(false){
						resultJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_FALSE);
						resultJson.put(ResponseJsonItems.MSG,"用户已登陆");
						
					}else{
						req.getSession().setAttribute("user", user.getNickname().toString());
						resultJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_SUCCESS);
						resultJson.put("user", JSONObject.toJSON(user));
					}
				}else{
						resultJson.put(ResponseJsonItems.STATUS,  ResponseJsonItems.STATUS_FALSE);
						resultJson.put(ResponseJsonItems.MSG,  "账号密码不匹配");
				}
			}else{
					resultJson.put(ResponseJsonItems.STATUS,  ResponseJsonItems.STATUS_FAILED);
					resultJson.put(ResponseJsonItems.MSG,  "用户名不存在");
			}
			resp.getWriter().write(resultJson.toString());
			session.close();
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}
}
