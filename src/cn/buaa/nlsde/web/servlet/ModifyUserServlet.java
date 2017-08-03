package cn.buaa.nlsde.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.buaa.nlsde.domain.User;
import cn.buaa.nlsde.exception.UserException;
import cn.buaa.nlsde.service.UserService;

public class ModifyUserServlet extends HttpServlet {
	private UserService userService = new UserService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		if(!password.equals(repassword) || password.isEmpty()){
			request.setAttribute("modify_error", "输入错误");
			request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request, response);
			return ;
		}
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			try {
				user = userService.modifyUser(user);
				request.getSession().setAttribute("user", user);
			} catch (UserException e) {
				request.setAttribute("modify_error", "输入错误");
				request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request, response);
				return ;
			}
		} catch (SQLException e) {
			request.setAttribute("modify_error", "更改失败");
			request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request, response);
			return ;
		}
		request.getRequestDispatcher("/modifyUserInfoSuccess.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
