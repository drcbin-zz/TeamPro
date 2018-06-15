package cn.edu.dlnu.url;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import cn.edu.dlnu.servlet.common.*;
import cn.edu.dlnu.servlet.models.*;
import cn.edu.dlnu.servlet.users.*;


@WebServlet("/web/*")
public class UrlServlet extends HttpServlet {
	static private Map<String, String> Url_ServletName_Map = new HashMap<>();
	static {
//		Url_ServletName_Map.put("^/static/.*$", StaticServlet.class.getName());
		Url_ServletName_Map.put("^.*/web/user/login$", LoginServlet.class.getName());
		Url_ServletName_Map.put("^.*/web/user/register$", RegisterServlet.class.getName());
		Url_ServletName_Map.put("^.*/web/user/changepassword$", ChangePasswordServlet.class.getName());
		Url_ServletName_Map.put("^.*/web/user/(\\d+)/(\\w+)/all$", MultipleObjectByUserId.class.getName());
		Url_ServletName_Map.put("^.*/web/(\\w+)/(\\d+)/detail$", SingleObjectServlet.class.getName());
		Url_ServletName_Map.put("^.*/web/(\\w+)/(\\d+)/delete$", SingleObjectDeleteServlet.class.getName());
		Url_ServletName_Map.put("^.*/web/(\\w+)/list$", MultipleObjectServlet.class.getName());
		Url_ServletName_Map.put("^.*/web/(\\w+)/insert$", SingleObjectInsertServlet.class.getName());
		Url_ServletName_Map.put("^.*/web/(\\w+)/update$", SingleObjectUpdateServlet.class.getName());
		Url_ServletName_Map.put("^.*/web/(\\d+)/friend/list$", FriendListServlet.class.getName());
		Url_ServletName_Map.put("^.*/web/comments$", CommentsServlet.class.getName());
		Url_ServletName_Map.put("^.*/web/apis$", ApiServlet.class.getName());
		Url_ServletName_Map.put("^.*/web/apis/(\\w+)/fields$", ModelFieldsServlet.class.getName());
//		Url_ServletName_Map.put(".*", ErrorServlet.class.getName());
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
												throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getRequestURI();
		Set<String> urlPatterns = Url_ServletName_Map.keySet();
		for (String urlPattern: urlPatterns) {
			System.out.println("url:" + url);
			if (Pattern.matches(urlPattern, url)) {
				System.out.println(urlPattern + ":匹配成功!");
				urlHandle(request, response, Url_ServletName_Map.get(urlPattern));
				return;
			}
			System.out.println(urlPattern + ":匹配失败！");
		}
		super.service(request, response);
	}
	
	private void urlHandle(HttpServletRequest request, HttpServletResponse response, String servletName) {
		try {
			// 鍔犺浇class
			Class<?> servlet = null;
			servlet = Class.forName(servletName);
			
			// 蹇界暐鏉冮檺鐨勫姞杞芥柟娉�
			Method serviceMethod = servlet.getDeclaredMethod("service", HttpServletRequest.class, HttpServletResponse.class);
			// 杩欐槸璁块棶鏉冮檺
			serviceMethod.setAccessible(true);
			serviceMethod.invoke(servlet.newInstance(), request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
