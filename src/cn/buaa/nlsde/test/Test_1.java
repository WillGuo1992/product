package cn.buaa.nlsde.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.buaa.nlsde.dao.OrderDao;
import cn.buaa.nlsde.dao.OrderItemDao;
import cn.buaa.nlsde.dao.UserDao;
import cn.buaa.nlsde.domain.Order;
import cn.buaa.nlsde.domain.OrderItem;
import cn.buaa.nlsde.domain.PageBean;
import cn.buaa.nlsde.domain.Product;
import cn.buaa.nlsde.domain.User;
import cn.buaa.nlsde.exception.UserException;
import cn.buaa.nlsde.servive.ProductService;
import cn.buaa.nlsde.utils.SendJMail;

public class Test_1 {
	@Test
	public void test1(){
		User user = new User();
		user.setUsername("hello");
		UserDao dao = new UserDao();
		try {
			dao.addUser(user);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test2(){
		SendJMail.sendMail("285257188@qq.com", "hello");
	}
	
	@Test
	public void test3(){
		ProductService productService = new ProductService();
		PageBean pageBean = productService.findProductByPage(3, 2, null);
		for(Product pd : pageBean.getPs()){
			System.out.println("----");
			System.out.println(pd.getId());
		}
		
	}
	@Test
	public void test4(){
		Order order = new Order();
		order.setId("123");
		order.setMoney(123);
		order.setUser_id(1);
		OrderDao orderDao = new OrderDao();
		try {
			orderDao.addOrder(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test5(){
		OrderItem orderItem = new OrderItem();
		Product product = new Product();
		product.setId("111");
		Order order = new Order();
		order.setId("123");
		orderItem.setP(product);
		orderItem.setOrder(order);
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		orderItems.add(orderItem);
		
		OrderItemDao dao = new OrderItemDao();
		dao.addOrderItems(orderItems);
	}
}
