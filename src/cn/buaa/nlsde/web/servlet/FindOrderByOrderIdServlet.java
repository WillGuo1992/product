package cn.buaa.nlsde.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.buaa.nlsde.domain.Order;
import cn.buaa.nlsde.service.OrderService;

public class FindOrderByOrderIdServlet extends HttpServlet {
	private OrderService orderService= new OrderService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Order order = null;
		try {
			order = orderService.finOrderByOrderId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("order", order);
		request.getRequestDispatcher("/orderInfo.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
