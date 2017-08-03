package cn.buaa.nlsde.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.buaa.nlsde.domain.User;
import cn.buaa.nlsde.exception.UserException;
import cn.buaa.nlsde.service.UserService;

public class LoginServlet extends HttpServlet {
	private UserService userService = new UserService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user.getUsername().isEmpty()||user.getPassword().isEmpty()){
			request.setAttribute("login_error", "用户名或密码不能为空");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return ;
		}
		try {
			user = userService.login(user);
		} catch (UserException e) {
			request.setAttribute("login_error",e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return ;
		}
		request.getSession().setAttribute("user", user);
		if(user.getRole().equals("admin")){
			request.getRequestDispatcher("/admin/login/home.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/showProductByPage").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
