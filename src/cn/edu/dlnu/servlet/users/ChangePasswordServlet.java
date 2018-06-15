package cn.edu.dlnu.servlet.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.dlnu.models.ModelsFactory;
import cn.edu.dlnu.models.User;
import cn.edu.dlnu.tools.ErrorCode;
import cn.edu.dlnu.tools.ResponseJsonItems;
import cn.edu.dlnu.tools.StringTool;
import cn.edu.dlnu.url.ModelFieldsServlet;

import com.alibaba.fastjson.JSONObject;

public class ChangePasswordServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String oldPassword = req.getParameter("old_password");
		String newPassword = req.getParameter("new_password");
		String userIdString = req.getParameter("id");
		Integer id = StringTool.changeToIntegerOrDefault(userIdString, null);
		
		JSONObject responseJson = new JSONObject();
		
		if(id == null){
			responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_FALSE);
			responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1005);
			responseJson.put(ResponseJsonItems.MSG, "请输入和旧密码不同的新密码");
			resp.getWriter().write(responseJson.toString());
			return;
		}else if (oldPassword == null || newPassword == null || newPassword.equals(oldPassword)){
			responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_FALSE);
			responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1005);
			responseJson.put(ResponseJsonItems.MSG, "请输入和旧密码不同的新密码");
			resp.getWriter().write(responseJson.toString());
			return;
		}
		
		User user = (User) ModelsFactory.selectByPrimaryKey(User.ModelName_STR, id);
		if (user != null){
			if (user.getPassword().equals(oldPassword)){
				user.setPassword(newPassword);
				ModelsFactory.updateByPrimaryKey(User.ModelName_STR, user);
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_SUCCESS);
			}else{
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
				responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1001);
				responseJson.put(ResponseJsonItems.MSG, "密码错误");
			}
		}else{
			responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
			responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1011);
			responseJson.put(ResponseJsonItems.MSG, "用户不存在");
		}
		
		resp.getWriter().write(responseJson.toString());
		
		
	}

}
