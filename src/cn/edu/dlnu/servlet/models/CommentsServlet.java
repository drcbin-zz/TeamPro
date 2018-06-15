package cn.edu.dlnu.servlet.models;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.edu.dlnu.models.Model;
import cn.edu.dlnu.models.ModelsFactory;
import cn.edu.dlnu.tools.ResponseJsonItems;
import cn.edu.dlnu.tools.StringTool;


public class CommentsServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		JSONObject resopnseJson = new JSONObject();
		
		String idString = request.getParameter("id");
		Integer id = StringTool.changeToIntegerOrDefault(idString, null);
		
		
		// check id
		if (id != null) {
			List<Model> models = ModelsFactory.getComments(id);
			resopnseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_SUCCESS);
			resopnseJson.put("comments", models);
		}else{
			resopnseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
		} // end check id
		
		response.getWriter().write(resopnseJson.toJSONString());
	}
	
}
