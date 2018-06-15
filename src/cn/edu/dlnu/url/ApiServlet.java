package cn.edu.dlnu.url;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;



public class ApiServlet extends HttpServlet {
	static JSONObject urlsJsonObject = new JSONObject();
	static JSONObject urlJsonObject = null;
//	"^.*/web/user/login$", LoginServlet.class.getName());
//	Url_ServletName_Map.put("^.*/web/user/register$", RegisterServlet.class.getName());
//	Url_ServletName_Map.put("^.*/web/user/(\\d+)/(\\w+)/all$", MultipleObjectByUserId.class.getName());
//	Url_ServletName_Map.put("^.*/web/(\\w+)/(\\d+)/detail$", SingleObjectServlet.class.getName());
//	Url_ServletName_Map.put("^.*/web/(\\w+)/list$",
	static {
		urlJsonObject = new JSONObject();
		urlJsonObject.put("patameter", "GET:null/POST:username:用户名, password: 登陆密码");
		urlJsonObject.put("type", "POST:登陆/GET:注销");
		urlJsonObject.put("url", ".*/web/user/login");
		urlsJsonObject.put("用户登陆", urlJsonObject);
		
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("patameter", "POST:username:用户名, password: 登陆密码");
		urlJsonObject.put("type", "POST:注册");
		urlJsonObject.put("url", ".*/web/user/register");
		urlsJsonObject.put("用户注册", urlJsonObject);
		
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("说明", "object_name:对象名字(user/spot/travel_memoary/friends_travel/comment), object_id:对象id号");
		urlJsonObject.put("type", "POST/GET");
		urlJsonObject.put("url", ".*/web/${object_name}/${object_id}/detail");
		urlsJsonObject.put("单一对象查询", urlJsonObject);
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("说明", "object_name:对象名字(spot/travel_memoary/friends_travel/comment)");
		urlJsonObject.put("paramter", "page:请求页数, count:每页个数,type:是否分类(仅travel_memoary)");
		urlJsonObject.put("type", "POST/GET");
		urlJsonObject.put("url", ".*/web/${object_name}/list");
		urlsJsonObject.put("多个对象查询", urlJsonObject);
		
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("说明", "object_name:对象名字(travel_memoary/friends_travel), user_id:用户id号");
		urlJsonObject.put("paramter", "page:请求页数, count:每页个数, type:是否分类(仅travel_memoary)");
		urlJsonObject.put("type", "POST/GET");
		urlJsonObject.put("url", ".*/web/user/${user_id}/${object_name}/all");
		urlsJsonObject.put("获取用户相关发布", urlJsonObject);
		
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("说明", "object_name:对象名字(spot/travel_memoary/friends_travel/comment/friend/)");
		urlJsonObject.put("type", "POST");
		urlJsonObject.put("url", ".*/web/${model_name}/insert");
		urlsJsonObject.put("新建一个对象", urlJsonObject);
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("说明", "object_name:对象名字(user/spot/travel_memoary/friends_travel/comment/)");
		urlJsonObject.put("type", "POST");
		urlJsonObject.put("url", ".*/web/${model_name}/update");
		urlsJsonObject.put("更新一个对象", urlJsonObject);
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("说明", "user_id:用户的id");
		urlJsonObject.put("type", "POST/GET");
		urlJsonObject.put("url", ".*/web/${user_id}/friends$");
		urlsJsonObject.put("好友列表", urlJsonObject);
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("说明", "object_name:对象名字(friend/user/comment/friendstravel/travelmemoary/spot)");
		urlJsonObject.put("type", "POST/GET");
		urlJsonObject.put("url", ".*/web/apis/${object_name}/fields");
		urlsJsonObject.put("模型字段查询", urlJsonObject);
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("paramter", "id:用户id, old_password:旧密码, new_password:新密码");
		urlJsonObject.put("type", "POST");
		urlJsonObject.put("url", ".*/web/user/changepassword");
		urlsJsonObject.put("用户修改密码", urlJsonObject);
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("说明", "object_name:对象名字(user/spot/travelmemoary/friendstravel/comment), object_id:对象id号");
		urlJsonObject.put("type", "GET/POST");
		urlJsonObject.put("url", ".*/web/${object_name}/${object_id}/delete");
		urlsJsonObject.put("单个对象删除", urlJsonObject);
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("type", "GET/POST");
		urlJsonObject.put("url", ".*/web/commetns");
		urlJsonObject.put("paramter", "id:被评论对象的id");
		urlsJsonObject.put("获取某个对象的评论", urlJsonObject);
	}

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		response.getWriter().write(urlsJsonObject.toString());
		

	}
	

}
