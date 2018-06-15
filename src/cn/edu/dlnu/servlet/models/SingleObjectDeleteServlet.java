package cn.edu.dlnu.servlet.models;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;


import cn.edu.dlnu.models.Model;
import cn.edu.dlnu.models.ModelsFactory;
import cn.edu.dlnu.models.User;
import cn.edu.dlnu.tools.ErrorCode;
import cn.edu.dlnu.tools.ResponseJsonItems;
import cn.edu.dlnu.tools.Status;
import cn.edu.dlnu.tools.StringTool;


/**
 * 说明：通过Id删除Model
 * 
 */

public class SingleObjectDeleteServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = req.getRequestURI();
		Pattern pattern = Pattern.compile("^.*/web/(\\w+)/(\\d+)/delete$");
		Matcher matcher = pattern.matcher(url);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		
		JSONObject responseJson = new JSONObject();
		
		// url check 
		if(matcher.find()){
			String modelName = matcher.group(1);
			String idString = matcher.group(2);
			Integer id = StringTool.changeToIntegerOrDefault(idString, null);
			
			Model model = ModelsFactory.selectByPrimaryKey(modelName, id);
			
			// check model 
			if(model != null){
				try {	
					Field status = model.getClass().getDeclaredField("status");
					status.setAccessible(true);
					System.out.println("before:" + status.get(model));
					status.set(model, Status.getStatusCode(Status.BAN));
					System.out.println("after:" + status.get(model));
					int result = ModelsFactory.updateByPrimaryKey(modelName, model);
					if(result == 0){
						responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_FAILED);
						responseJson.put(ResponseJsonItems.MSG, "更新失败");
					}else{
						responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_SUCCESS);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
					responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1040);
					responseJson.put(ResponseJsonItems.MSG, "未知错误");
				}
			}else{
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
				responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1031);
				responseJson.put(ResponseJsonItems.MSG, "对象不存在");
			} // end check model		
		}else{
			responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
			responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1040);
			responseJson.put(ResponseJsonItems.MSG, "url错误");
		} // end url check
		
		resp.getWriter().write(responseJson.toString());
	
	}

}
