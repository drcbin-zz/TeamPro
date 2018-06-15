package cn.edu.dlnu.servlet.models;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.alibaba.fastjson.JSONObject;

import cn.edu.dlnu.models.Comment;
import cn.edu.dlnu.models.Friend;
import cn.edu.dlnu.models.FriendsTravel;
import cn.edu.dlnu.models.Model;
import cn.edu.dlnu.models.ModelsFactory;
import cn.edu.dlnu.models.Spot;
import cn.edu.dlnu.models.TravelMemoary;
import cn.edu.dlnu.models.User;
import cn.edu.dlnu.tools.ErrorCode;
import cn.edu.dlnu.tools.ResponseJsonItems;


/**
 * 
 * 说明：新建信息
 * 
 */



public class SingleObjectInsertServlet extends HttpServlet{

	static public Map<String, String> ModelName_ModelName_Map = new HashMap<String, String>();
	static{
		ModelName_ModelName_Map.put("user", User.ModelName_STR);
		ModelName_ModelName_Map.put("spot", Spot.ModelName_STR);
		ModelName_ModelName_Map.put("travel_memoary", TravelMemoary.ModelName_STR);
		ModelName_ModelName_Map.put("friends_travel", FriendsTravel.ModelName_STR);
		ModelName_ModelName_Map.put("comment", Comment.ModelName_STR);
		ModelName_ModelName_Map.put("friend", Friend.ModelName_STR);
	}
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取urls
		String url = request.getRequestURI();
		System.out.println(url);
		//正则表达式
		Pattern pattern = Pattern.compile("^.*/web/(\\w+)/insert$");
		Matcher matcher = pattern.matcher(url);	
		String modelName = null;
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		JSONObject responseJson = new JSONObject();
		
		
		// 检验url是否正确
		if(matcher.find()){
			modelName = matcher.group(1);
			modelName = ModelName_ModelName_Map.get(modelName);
			try {
				Map<String, String[]> dataMap = request.getParameterMap();
				Model model = null;
			  	model = ModelsFactory.create(modelName, dataMap);
			  	// check model
			  	if (model != null){
			  		int flag = ModelsFactory.insert(modelName, model);
			  		// check insert result
					if (flag != 0){
						responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_SUCCESS);			
					}else{
						responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.ERROR_CODE);
						responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E0000);
						responseJson.put(ResponseJsonItems.MSG, "未知错误");	
					} // end check insert result
					
			  	}else{
			  		responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.ERROR_CODE);
			  		responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1012);
			  		responseJson.put(ResponseJsonItems.MSG, "信息不完整!");

			  	}// end check model
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.ERROR_CODE);
		  		responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1012);
		  		responseJson.put(ResponseJsonItems.MSG, "信息不完整!");	
				e.printStackTrace();
			} // end try
		}else{
			responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.ERROR_CODE);
	  		responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1031);
	  		responseJson.put(ResponseJsonItems.MSG, "url错误!");
			
		}  // end check url	
		
		response.getWriter().write(responseJson.toString());		
	} // end service
}
