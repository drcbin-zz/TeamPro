package cn.edu.dlnu.servlet.models;

import java.io.IOException;
import java.util.*;

import java.util.regex.*;


import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.alibaba.fastjson.*;
import cn.edu.dlnu.models.*;
import cn.edu.dlnu.tools.*;

/**
 * 
 * 说明：通过Id查找Model
 * 
 */
public class SingleObjectServlet extends HttpServlet {
	static public Map<String, String> ModelName_ModelName_Map = new HashMap<String, String>();
	static{
		ModelName_ModelName_Map.put("user", User.ModelName_STR);
		ModelName_ModelName_Map.put("spot", Spot.ModelName_STR);
		ModelName_ModelName_Map.put("travel_memoary", TravelMemoary.ModelName_STR);
		ModelName_ModelName_Map.put("friends_travel", FriendsTravel.ModelName_STR);
		ModelName_ModelName_Map.put("comment", Comment.ModelName_STR);
//		ModelName_ModelName_Map.put("friend", Spot.ModelName_STR);
	}
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url =request.getRequestURI();
		
		String patternStr = "^.*/web/(\\w+)/(\\d+)/detail/?$";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(url);
		String modelName = null, idStr = null;
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		JSONObject responseJson = new JSONObject();

		
		System.out.println("in singleonb");
		//  匹配url
		if (matcher.find()) {
			modelName = matcher.group(1);
			modelName = ModelName_ModelName_Map.get(modelName);
			if (modelName == null){
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
				responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1012);
				responseJson.put(ResponseJsonItems.MSG, "请求错误");
				response.getWriter().write(responseJson.toString());
				return;
				
			}
			idStr = matcher.group(2);
			Integer id = StringTool.changeToIntegerOrDefault(idStr, null);
			
			// id号非整形
			if (id == null) {
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
				responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1012);
				responseJson.put(ResponseJsonItems.MSG, "请求错误");
				response.getWriter().write(responseJson.toString());
				return;
			}
			

			Model model = ModelsFactory.selectByPrimaryKey(modelName, id);
			
			// 判断模型为null
			if (model == null) {
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
				responseJson.put(ResponseJsonItems.MSG, "错误请求");
			}else {
				System.out.println("modelName:" + modelName);
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_SUCCESS);
				responseJson.put("object", JSON.toJSON(model));
			}
		}else {
			responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
			responseJson.put(ResponseJsonItems.MSG, "错误url");	
		}
		
		response.getWriter().write(responseJson.toString());
	}
}
