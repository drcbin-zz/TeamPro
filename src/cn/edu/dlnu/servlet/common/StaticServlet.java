package cn.edu.dlnu.servlet.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.sun.jndi.toolkit.url.Uri;


public class StaticServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getRequestURI();
		System.out.println(path);
//		request.getRequestDispatcher("/static/html/users/login.html").forward(request, response);
		System.out.println("hhheee");
		super.service(request, response);
	}
}