package cn.edu.dlnu.servlet.models;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.alibaba.fastjson.*;

import cn.edu.dlnu.models.*;
import cn.edu.dlnu.tools.ErrorCode;
import cn.edu.dlnu.tools.ResponseJsonItems;
import cn.edu.dlnu.tools.StringTool;


/**
 * 说明：遍历所有信息
 */

public class MultipleObjectServlet extends HttpServlet{

	static public Map<String, String> ModelName_ModelName_Map = new HashMap<String, String>();
	static{
		ModelName_ModelName_Map.put("user", User.ModelName_STR);
		ModelName_ModelName_Map.put("spot", Spot.ModelName_STR);
		ModelName_ModelName_Map.put("travel_memoary", TravelMemoary.ModelName_STR);
		ModelName_ModelName_Map.put("friends_travel", FriendsTravel.ModelName_STR);
		ModelName_ModelName_Map.put("comment", Comment.ModelName_STR);
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取url
		String url = request.getRequestURI();
		System.out.println(url);
		// 正则表达式
		String patternStr = "^.*/web/(\\w+)/list$";
		Pattern pattern = Pattern.compile(patternStr);
		
		Matcher matcher = pattern.matcher(url);
		String modelName = null,pagest = null,countst = null;
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		
		JSONObject responseJson = new JSONObject();
		// 验证一下
		System.out.println("MultipleObjectServlet");
		
		// 匹配url
		if(matcher.find()){
			// modelName
			modelName = matcher.group(1);
			System.out.println(modelName);
			modelName = ModelName_ModelName_Map.get(modelName);
			if(modelName == null){
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
				//		ErrorCode后期改
				responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1012);
				responseJson.put(ResponseJsonItems.MSG, "请求错误！");
				response.getWriter().write(responseJson.toString());
				return;
			}
			//分页
			pagest = request.getParameter("page");
			Integer page = StringTool.changeToIntegerOrDefault(pagest, null);
			System.out.println(page);
			countst = request.getParameter("count");
			Integer count = StringTool.changeToIntegerOrDefault(countst, null);
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
			
			List<Model> models = ModelsFactory.selectAll(modelName,map);
			Model model = null;
			for (int i = 0 ; i < models.size(); i++) {
				model = models.get(i);
				if(ModelsFactory.isActive(model) == false){
					models.remove(i);
				}
			}
			
			// 判断模型是否为空
			if(models.size() == 0){
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
				responseJson.put(ResponseJsonItems.MSG, "查询不存在!");
			}else{
				responseJson.put(ResponseJsonItems.STATUS,ResponseJsonItems.STATUS_SUCCESS );	
				
				// check 是否分类
				if (type != null && modelName.equals("travelmemoary")){
					Map<String, List<Model>> type_Models_map = ModelsFactory.categoryByType(models);
					responseJson.put("types", type_Models_map);
				}else{
					responseJson.put("object", JSON.toJSON(models));
				}	
			}
		}else{
			responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
			responseJson.put(ResponseJsonItems.MSG,"错误url");
		}
		response.getWriter().write(responseJson.toString());
	}	
	

	
}
