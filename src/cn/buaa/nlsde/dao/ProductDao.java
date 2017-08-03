package cn.buaa.nlsde.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.buaa.nlsde.domain.Order;
import cn.buaa.nlsde.domain.OrderItem;
import cn.buaa.nlsde.domain.PageBean;
import cn.buaa.nlsde.domain.Product;
import cn.buaa.nlsde.utils.C3P0Util;

public class ProductDao {

		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		public int findAllCount(String category) {
		List list= new ArrayList<String>();
		int count=0;
		String sql = "select count(*) from products where 1=1 ";
		if(category!=null && !category.isEmpty()){
			sql += "and category = ? ";
			list.add(category);
		}
		try {
			Object[] obj = qr.query(sql, new ArrayHandler(),list.toArray());
			count = Integer.valueOf(obj[0].toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("count:"+count);
		return count;
	}

	public PageBean findByPage(int pageNum, int pageSize, String category) {
		int pageFrom= (pageNum-1)*pageSize;
		PageBean pageBean = new PageBean();
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List list= new ArrayList<String>();
		String sql = "select * from products where 1=1 ";
		if(category!= null && !category.isEmpty()){
			sql += "and category = ? ";
			list.add(category);
		}
		sql += " limit "+pageFrom+","+pageSize;
		try {
			System.out.println(sql);
			List<Product> ps = qr.query(sql, new BeanListHandler<Product>(Product.class),list.toArray());
			pageBean.setPs(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageBean;
	}

	public PageBean findBySearch(String name) {
		PageBean pageBean = new PageBean();
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List list= new ArrayList<String>();
		String sql = "select * from products where 1=1 ";
		if(name!= null  ){
			sql += "and name LIKE ? ";
			list.add("%"+name+"%");
		}
		try {
			System.out.println(sql);
			List<Product> ps = qr.query(sql, new BeanListHandler<Product>(Product.class),list.toArray());
			pageBean.setPs(ps);
			pageBean.setTotalCount(ps.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageBean;
	}

	public Product findProductById(String id) {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		List list= new ArrayList<String>();
		String sql = "select * from products where 1=1 ";
		Product product = null;
		if(id!= null  ){
			sql += "and id = ? ";
			list.add(id);
		}
		try {
			System.out.println(sql);
			product = qr.query(sql, new BeanHandler<Product>(Product.class),list.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	public void changeProductNum(List<OrderItem> orderItems) throws SQLException {
		QueryRunner qr =new QueryRunner();
		String sql = "update products set pnum = ? where id = ?  ";
		Object[][] params = new Object[orderItems.size()][];
		for(int i=0;i<orderItems.size();i++){
			params[i] = new Object[]{orderItems.get(i).getP().getPnum()-orderItems.get(i).getBuynum(),orderItems.get(i).getP().getId()};
		}
		qr.batch(C3P0Util.getConnection(),sql, params);
		
	}

}
