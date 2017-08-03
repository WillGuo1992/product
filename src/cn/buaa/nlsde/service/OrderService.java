package cn.buaa.nlsde.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import sun.jdbc.odbc.OdbcDef;

import cn.buaa.nlsde.dao.OrderDao;
import cn.buaa.nlsde.dao.OrderItemDao;
import cn.buaa.nlsde.dao.ProductDao;
import cn.buaa.nlsde.domain.Order;
import cn.buaa.nlsde.domain.OrderItem;
import cn.buaa.nlsde.domain.Product;
import cn.buaa.nlsde.domain.User;

public class OrderService {
	private OrderDao orderDao = new OrderDao();
	private OrderItemDao orderItemDao = new OrderItemDao();
	private ProductDao productDao = new ProductDao();
	public void addOrder(Order order, List<OrderItem> orderItems) throws SQLException {
		
		orderDao.addOrder(order);
		orderItemDao.addOrderItems(orderItems);
		productDao.changeProductNum(orderItems);
	}
	public List<Order> findOrdersByUser(User user) throws SQLException {
		List<Order> orders = orderDao.findOrderByUser(user);
		return orders;
	}
	public Order finOrderByOrderId(String id) throws SQLException {
		Order order = orderDao.finOrderByOrderId(id);
		return order;
	}
	public void deleteOrderById(String id) throws SQLException {
		orderItemDao.deleteOrderItemById(id);
		orderDao.deleteOrderById(id);
		
	}
	public void modifyOrderState(String OrderId) throws SQLException {
		orderDao.modifyOrderState(OrderId);
		
	}

}
