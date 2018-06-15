package cn.edu.dlnu.servlet.models;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import cn.edu.dlnu.models.*;
import cn.edu.dlnu.tools.*;

import com.alibaba.fastjson.*;

/**
 * 说明：通过ID查找Model
 * 
 */

public class MultipleObjectByUserId extends HttpServlet {
	static public Map<String, String> ModelName_ModelName_Map = new HashMap<String, String>();
	static{
		ModelName_ModelName_Map.put("travel_memoary", TravelMemoary.ModelName_STR);
		ModelName_ModelName_Map.put("friends_travel", FriendsTravel.ModelName_STR);
		ModelName_ModelName_Map.put("comment", Comment.ModelName_STR);
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url =request.getRequestURI();
		Pattern pattern = Pattern.compile("^.*/web/user/(\\d+)/(\\w+)/all$");
		Matcher matcher = pattern.matcher(url);
		String modelName = null, idStr = null,pagestr = null, countstr = null;
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		
		JSONObject responseJson = new JSONObject();

		// 检测url是否正确
		if (matcher.find()){
			idStr = matcher.group(1);
			modelName = matcher.group(2);
			modelName = ModelName_ModelName_Map.get(modelName);
			
			// 分页	
			pagestr = request.getParameter("page");
			Integer page = StringTool.changeToIntegerOrDefault(pagestr, null);
			System.out.println(page);
			countstr = request.getParameter("count");
			Integer count = StringTool.changeToIntegerOrDefault(countstr, null);
			System.out.println(count);
			Map<String, Integer> map = new HashMap<>();
			if(page == null){
				page = 1;
			}
			if(count == null){
				count = 20;
			}
			map.put("start", (page-1)*count);
			map.put("end", count);
		
			// 检测对象名正确性
			if (modelName != null){
				List<Model> models = ModelsFactory.selectByAuthId(modelName, new Integer(idStr),map);
				// check 是否分类
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_SUCCESS);
				if (type != null && modelName.equals("travelmemoary")){
					Map<String, List<Model>> type_Models_map = ModelsFactory.categoryByType(models);
					responseJson.put("types", type_Models_map);
				}else{
					responseJson.put("objects", models);
				}	
			}else{
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
				responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1032);
				responseJson.put(ResponseJsonItems.MSG, "对象名错误");	
			}
		}else{
			responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
			responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1031);
			responseJson.put(ResponseJsonItems.MSG, "错误url");	
		}
		response.getWriter().write(responseJson.toString());
	}
}
