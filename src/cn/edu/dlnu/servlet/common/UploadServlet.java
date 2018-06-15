package cn.edu.dlnu.servlet.common;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;


import cn.edu.dlnu.models.ModelsFactory;
import cn.edu.dlnu.models.User;
import cn.edu.dlnu.tools.HttpUtil;
import cn.edu.dlnu.tools.ResponseJsonItems;
import cn.edu.dlnu.tools.StringTool;


@WebServlet("/user/image/upload")
public class UploadServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
        response.setContentType("");
        response.setCharacterEncoding("utf-8");
        
        JSONObject responseJson = new JSONObject();
        
        String savePath = "E:\\workspace\\TeamPro\\WebContent\\FrontEnd\\MyImage\\";
        String fileName = null;
        
        fileName = HttpUtil.upload(request, "image", savePath);
        
        String idString = request.getParameter("id");
        Integer id = StringTool.changeToIntegerOrDefault(idString, null);
        User user = null;
		
        if(id != null){
        	user = (User) ModelsFactory.selectByPrimaryKey(User.ModelName_STR, id);
        }
        if (user != null){
        	user.setImagepath("/TeamPro/FrontEnd/MyImage/" + fileName);
        	ModelsFactory.updateByPrimaryKey(User.ModelName_STR, user);
        	responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_SUCCESS);
        	response.getWriter().write(responseJson.toString());
        	return;
        }  
        responseJson.put(ResponseJsonItems.STATUS, ResponseJsonItems.STATUS_ERROR);
    	response.getWriter().write(responseJson.toString());
    }
    
}