package cn.buaa.nlsde.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.buaa.nlsde.domain.User;
import cn.buaa.nlsde.exception.UserException;
import cn.buaa.nlsde.service.UserService;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
		String ckcode = (String) request.getParameter("ckcode");
		//验证码
		if(!checkcode_session.equals(ckcode)){
			request.setAttribute("ckcode_error", "验证码错误");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return ;
		}
		//bean封装
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		user.setActiveCode(UUID.randomUUID().toString());
		UserService userService = new UserService();
		try {
			userService.register(user);
		} catch (UserException e) {
			request.setAttribute("register_error", e.getMessage());
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return ;
		} 						
		response.sendRedirect(request.getContextPath()+"/registersuccess.jsp");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
