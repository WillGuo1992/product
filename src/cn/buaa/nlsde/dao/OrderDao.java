package cn.buaa.nlsde.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.buaa.nlsde.domain.Order;
import cn.buaa.nlsde.domain.User;
import cn.buaa.nlsde.utils.C3P0Util;

public class OrderDao {

	public void addOrder(Order order) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "insert into orders(id,money,receiverAddress,receiverName,receiverPhone,paystate,user_id) values (?,?,?,?,?,?,?)";
		List<Object> list = new ArrayList<Object>();
		list.add(order.getId());
		list.add(order.getMoney());
		list.add(order.getReceiverAddress());
		list.add(order.getReceiverName());
		list.add(order.getReceiverPhone());
		list.add(order.getPaystate());
		list.add(order.getUser_id());
		qr.update(sql, list.toArray());
	}

	public List<Order> findOrderByUser(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from orders where user_id = ? ";
		List<Order> orders = qr.query(sql,new BeanListHandler<Order>(Order.class),user.getId());
		return orders;
	}

	public Order finOrderByOrderId(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from orders where id = ? ";
		Order order = qr.query(sql,new BeanHandler<Order>(Order.class),id);
		return order;
		
	}

	public void deleteOrderById(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "delete from orders where id = ?  ";
		qr.update(sql, id);
	}

	public void modifyOrderState(String orderId) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "update orders set paystate = 1 where id = ? ";
		qr.update(sql, orderId);
	}

}
