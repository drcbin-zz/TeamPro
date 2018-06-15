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
		urlJsonObject.put("patameter", "GET:null/POST:username:�û���, password: ��½����");
		urlJsonObject.put("type", "POST:��½/GET:ע��");
		urlJsonObject.put("url", ".*/web/user/login");
		urlsJsonObject.put("�û���½", urlJsonObject);
		
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("patameter", "POST:username:�û���, password: ��½����");
		urlJsonObject.put("type", "POST:ע��");
		urlJsonObject.put("url", ".*/web/user/register");
		urlsJsonObject.put("�û�ע��", urlJsonObject);
		
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("˵��", "object_name:��������(user/spot/travel_memoary/friends_travel/comment), object_id:����id��");
		urlJsonObject.put("type", "POST/GET");
		urlJsonObject.put("url", ".*/web/${object_name}/${object_id}/detail");
		urlsJsonObject.put("��һ�����ѯ", urlJsonObject);
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("˵��", "object_name:��������(spot/travel_memoary/friends_travel/comment)");
		urlJsonObject.put("paramter", "page:����ҳ��, count:ÿҳ����,type:�Ƿ����(��travel_memoary)");
		urlJsonObject.put("type", "POST/GET");
		urlJsonObject.put("url", ".*/web/${object_name}/list");
		urlsJsonObject.put("��������ѯ", urlJsonObject);
		
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("˵��", "object_name:��������(travel_memoary/friends_travel), user_id:�û�id��");
		urlJsonObject.put("paramter", "page:����ҳ��, count:ÿҳ����, type:�Ƿ����(��travel_memoary)");
		urlJsonObject.put("type", "POST/GET");
		urlJsonObject.put("url", ".*/web/user/${user_id}/${object_name}/all");
		urlsJsonObject.put("��ȡ�û���ط���", urlJsonObject);
		
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("˵��", "object_name:��������(spot/travel_memoary/friends_travel/comment/friend/)");
		urlJsonObject.put("type", "POST");
		urlJsonObject.put("url", ".*/web/${model_name}/insert");
		urlsJsonObject.put("�½�һ������", urlJsonObject);
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("˵��", "object_name:��������(user/spot/travel_memoary/friends_travel/comment/)");
		urlJsonObject.put("type", "POST");
		urlJsonObject.put("url", ".*/web/${model_name}/update");
		urlsJsonObject.put("����һ������", urlJsonObject);
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("˵��", "user_id:�û���id");
		urlJsonObject.put("type", "POST/GET");
		urlJsonObject.put("url", ".*/web/${user_id}/friends$");
		urlsJsonObject.put("�����б�", urlJsonObject);
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("˵��", "object_name:��������(friend/user/comment/friendstravel/travelmemoary/spot)");
		urlJsonObject.put("type", "POST/GET");
		urlJsonObject.put("url", ".*/web/apis/${object_name}/fields");
		urlsJsonObject.put("ģ���ֶβ�ѯ", urlJsonObject);
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("paramter", "id:�û�id, old_password:������, new_password:������");
		urlJsonObject.put("type", "POST");
		urlJsonObject.put("url", ".*/web/user/changepassword");
		urlsJsonObject.put("�û��޸�����", urlJsonObject);
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("˵��", "object_name:��������(user/spot/travelmemoary/friendstravel/comment), object_id:����id��");
		urlJsonObject.put("type", "GET/POST");
		urlJsonObject.put("url", ".*/web/${object_name}/${object_id}/delete");
		urlsJsonObject.put("��������ɾ��", urlJsonObject);
		
		urlJsonObject = new JSONObject();
		urlJsonObject.put("type", "GET/POST");
		urlJsonObject.put("url", ".*/web/commetns");
		urlJsonObject.put("paramter", "id:�����۶����id");
		urlsJsonObject.put("��ȡĳ�����������", urlJsonObject);
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
