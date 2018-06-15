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
 * ˵���������б�
 */
public class FriendListServlet extends HttpServlet{

	static public Map<String, String> ModelName_ModelName_Map = new HashMap<String, String>();
	static{
		ModelName_ModelName_Map.put("friend", Friend.ModelName_STR);
	}
	@Override

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//��ȡurl
		String url = request.getRequestURI();
		System.out.println(url);
		
		//������ʽ
		String patternStr = "^.*/web/(\\d+)/friend/list$";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(url);
		String idstr = null;
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		JSONObject responseJson = new JSONObject();
		
		//����url
		if(matcher.find()){
			idstr = matcher.group(1);
			Integer id = StringTool.changeToIntegerOrDefault(idstr, null);
			//idû��ȡ��
			if(id == null){
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
				responseJson.put(ResponseJsonItems.ERROR_CODE, ErrorCode.E1012);
				responseJson.put(ResponseJsonItems.MSG, "δ������ȷ��id");
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
			
			//ͨ��idû�ҵ������б�
			if(friends == null){
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
				responseJson.put(ResponseJsonItems.MSG, "id������");
			}else{
				responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_SUCCESS);
				responseJson.put("object", JSON.toJSON(friends));
				
			}//�ҵ��˺����б�
		}else{
			responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.ERROR_CODE);
			responseJson.put(ResponseJsonItems.MSG, "url����");
		}//url����
		
		response.getWriter().write(responseJson.toString());
		
	}
	
	

}
