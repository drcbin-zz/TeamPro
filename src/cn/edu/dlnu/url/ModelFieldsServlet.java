package cn.edu.dlnu.url;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.dlnu.models.Comment;
import cn.edu.dlnu.models.Friend;
import cn.edu.dlnu.models.FriendsTravel;
import cn.edu.dlnu.models.Spot;
import cn.edu.dlnu.models.TravelMemoary;
import cn.edu.dlnu.models.User;
import cn.edu.dlnu.tools.ModelType;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

public class ModelFieldsServlet extends HttpServlet{
	public static Map<String, JSONObject> ModelName_Fields_Map = new HashMap<>();
	private static JSONObject  userFields = new JSONObject(); 
	private static JSONObject  commentFields = new JSONObject(); 
	private static JSONObject  friendsTravelFields = new JSONObject(); 
	private static JSONObject  travelMemoaryFields = new JSONObject(); 
	private static JSONObject  spotFields = new JSONObject(); 
	private static JSONObject  friendFields = new JSONObject(); 
	
	static {
		// user
		userFields.put("id", "insert-N/update-Y");
		userFields.put("status", "insert-N/update-Y");
		userFields.put("name", "insert-Y/update-N");
		userFields.put("email", "insert-O/update-O");
		userFields.put("qq", "insert-O/update-O");
		userFields.put("address", "insert-O/update-O");
		userFields.put("phone", "insert-O/update-O");
		userFields.put("birthday", "insert-O/update-O");
		userFields.put("gender", "insert-O/update-O");
		userFields.put("createTime", "/");
		userFields.put("lastTime", "/");
		userFields.put("temp", "insert-O/update-O");
		
		// comment
		commentFields.put("id", "insert-N/");
		commentFields.put("status", "insert-N/insert-Y");
		commentFields.put("authId", "insert-Y/");
		commentFields.put("objectType", "insert-Y/");
		commentFields.put("objectId", "insert-Y/");
		commentFields.put("title", "insert-Y/");
		commentFields.put("comment", "insert-Y/");
		commentFields.put("createTime", "/");
		
		
		// Friend
		friendFields.put("id", "insert-N/update-Y");
		friendFields.put("from", "insert-Y/");
		friendFields.put("to", "insert-Y/");
		friendFields.put("createTime", "/");
		
		
		// friendsTravelFields
		friendsTravelFields.put("id", "insert-N/update-Y");
		friendsTravelFields.put("userId", "insert-Y/");
		friendsTravelFields.put("spotId", "insert-Y/");
		friendsTravelFields.put("title", "insert-Y/update-Y");
		friendsTravelFields.put("fromTime", "insert-Y/update-Y");
		friendsTravelFields.put("toTime", "insert-Y/update-Y");
		friendsTravelFields.put("content", "insert-Y/update-Y");
		friendsTravelFields.put("createTime", "insert-N/update-N");
		friendsTravelFields.put("distination", "insert-O/update-O");
		
		// travelMemoaryFields
		travelMemoaryFields.put("id", "insert-N/update-Y");
		travelMemoaryFields.put("status", "insert-Y/update-Y");
		travelMemoaryFields.put("authId", "insert-Y/");
		travelMemoaryFields.put("content", "insert-Y/update-Y");
		travelMemoaryFields.put("cdate", "/");
		travelMemoaryFields.put("imagePath", "insert-O/update-O");
		travelMemoaryFields.put("likeCount", "/");
		travelMemoaryFields.put("type_name", "insert-Y/update-Y");
		travelMemoaryFields.put("id", "insert-N/update-Y");
		travelMemoaryFields.put("id", "insert-N/update-Y");
		travelMemoaryFields.put("id", "insert-N/update-Y");
		travelMemoaryFields.put("id", "insert-N/update-Y");
		
		// spotFields
		spotFields.put("id", "insert-N/update-Y");
		spotFields.put("status", "insert-Y/update-Y");
		spotFields.put("content", "insert-Y/update-Y");
		spotFields.put("spot_name", "insert-Y/update-Y");
		spotFields.put("spot_type", "insert-Y/update-Y");
		spotFields.put("city", "insert-Y/update-Y");
		spotFields.put("suit_people", "insert-O/update-O");
		spotFields.put("suit_time", "insert-O/update-O");
		spotFields.put("need_things", "insert-O/update-O");
		spotFields.put("attentions", "insert-O/update-O");
		spotFields.put("detail", "insert-Y/update-Y");
		spotFields.put("likeCount", "/");
		spotFields.put("image_path", "insert-O/update-O");


	}
	
	static {
		ModelName_Fields_Map.put(User.ModelName_STR, userFields);
		ModelName_Fields_Map.put(Comment.ModelName_STR, commentFields);
		ModelName_Fields_Map.put(FriendsTravel.ModelName_STR, friendsTravelFields);
		ModelName_Fields_Map.put(TravelMemoary.ModelName_STR, travelMemoaryFields);
		ModelName_Fields_Map.put(Spot.ModelName_STR, spotFields);
		ModelName_Fields_Map.put(Friend.ModelName_STR, friendFields); 
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		Pattern pattern = Pattern.compile("^.*/web/apis/(\\w+)/fields$");
		String url = request.getRequestURI();
		Matcher matcher = pattern.matcher(url);
		
		if (matcher.find()){
			String modelName = matcher.group(1);
			JSONObject fields = ModelName_Fields_Map.get(modelName);
			if (fields != null){
				response.getWriter().write(fields.toString());
				return;
			}	
		}
		response.getWriter().write("name error");
	}
	
	
}
