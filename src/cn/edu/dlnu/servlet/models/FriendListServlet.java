package cn.edu.dlnu.servlet.models;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.edu.dlnu.models.Friend;
import cn.edu.dlnu.models.ModelsFactory;
import cn.edu.dlnu.tools.ErrorCode;
import cn.edu.dlnu.tools.ResponseJsonItems;
import cn.edu.dlnu.tools.StringTool;

/**
 * 说明：好友列表
 */
public class FriendListServlet extends HttpServlet{

	static public Map<String, String> ModelName_ModelName_Map = new HashMap<String, String>();
	static{
		ModelName_ModelName_Map.put("friend", Friend.ModelName_STR);
	}
	@Override

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//获取url
		String url = request.getRequestURI();
		System.out.println(url);
		
		//正则表达式
		String patternStr = "^.*/web/(\\d+)/friend/list$";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(url);
		String idstr = null;
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		JSONObject responseJson = new JSONObject();
		
		//检验url
		if(matcher.find()){
			idstr = matcher.group(1);
			Integer id = StringTool.changeToIntegerOrDefault(idstr, null);
			//id没获取到
			if(id == null){
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
				responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1012);
				responseJson.put(ResponseJsonItems.MSG, "未输入正确的id");
				response.getWriter().write(responseJson.toString());
				return;
			}
			List<Friend> friends = null;
			try {
				friends = ModelsFactory.friendsList(id);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			//通过id没找到好友列表
			if(friends == null){
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
				responseJson.put(ResponseJsonItems.MSG, "id不存在");
			}else{
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_SUCCESS);
				responseJson.put("object", JSON.toJSON(friends));
				
			}//找到了好友列表
		}else{
			responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.ERROR_CODE);
			responseJson.put(ResponseJsonItems.MSG, "url错误");
		}//url错误
		
		response.getWriter().write(responseJson.toString());
		
	}
	
	

}
