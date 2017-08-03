package cn.buaa.nlsde.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.buaa.nlsde.domain.Order;
import cn.buaa.nlsde.domain.OrderItem;
import cn.buaa.nlsde.domain.Product;
import cn.buaa.nlsde.domain.User;
import cn.buaa.nlsde.service.OrderService;

public class CreateOrderServlet extends HttpServlet {
	private OrderService orderService = new OrderService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return ;
		}
		Map<Product,Integer> carts = (Map<Product, Integer>) request.getSession().getAttribute("carts");
		Order order = new Order();
		try {
			BeanUtils.populate(order, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		double sumMoney = 0 ;
		for(Product product : carts.keySet()){
			sumMoney+= product.getPrice()*carts.get(product);
		}
		order.setId(UUID.randomUUID().toString());
		order.setPaystate(0);
		order.setMoney(sumMoney);
		order.setUser_id(user.getId());
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for(Product product : carts.keySet()){
			OrderItem orderItem = new OrderItem();
			orderItem.setP(product);
			orderItem.setOrder(order);
			orderItem.setBuynum(carts.get(product));
			orderItems.add(orderItem);
		}
		order.setOrderItems(orderItems);
		try {
			orderService.addOrder(order,orderItems);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return ;
		}
		request.getSession().setAttribute("order", order);
		response.sendRedirect(request.getContextPath()+"/createOrderSuccess.jsp");
		
	}	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
