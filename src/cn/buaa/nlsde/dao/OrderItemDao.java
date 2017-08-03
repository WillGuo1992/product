package cn.buaa.nlsde.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;

import cn.buaa.nlsde.domain.Order;
import cn.buaa.nlsde.domain.OrderItem;
import cn.buaa.nlsde.domain.Product;
import cn.buaa.nlsde.utils.C3P0Util;

public class OrderItemDao {

	public void addOrderItems(List<OrderItem> orderItems) {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orderitem(order_id,product_id,buynum) values (?,?,?)";
		Object[][] params = new Object[orderItems.size()][];
		for(int i=0;i<params.length;i++){
			params[i] = new Object[]{orderItems.get(i).getOrder().getId(),orderItems.get(i).getP().getId(),orderItems.get(i).getBuynum()};
		}
		try {
			qr.batch(C3P0Util.getConnection(), sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteOrderItemById(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "delete from orderitem where order_id = ? ";
		qr.update(sql, id);
		
	}

}
