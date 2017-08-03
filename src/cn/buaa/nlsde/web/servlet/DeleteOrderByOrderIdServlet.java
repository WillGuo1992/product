package cn.buaa.nlsde.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.buaa.nlsde.service.OrderService;

public class DeleteOrderByOrderIdServlet extends HttpServlet {
	private OrderService orderService = new OrderService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			orderService.deleteOrderById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/findOrdersByUser").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
