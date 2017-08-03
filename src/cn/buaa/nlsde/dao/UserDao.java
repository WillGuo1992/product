package cn.buaa.nlsde.dao;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.buaa.nlsde.domain.User;
import cn.buaa.nlsde.exception.UserException;
import cn.buaa.nlsde.utils.C3P0Util;

public class UserDao {
	//添加用户
	public void addUser(User user) throws UserException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "insert into user(username,password,gender,email,telephone,introduce,activeCode) "
				+ "values (?,?, ?,?,?,?,?)";
		try {
			qr.update(sql, user.getUsername(), user.getPassword(),
					user.getGender(), user.getEmail(), user.getTelephone(),
					user.getIntroduce(), user.getActiveCode());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserException("注册失败");
		}

	}
	//查找用户名是否已存在
	public boolean findUserByUsername(String username) {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from user where username = ? ";
		try {
			User user = qr.query(sql, new BeanHandler<User>(User.class),username);
			if(user==null)
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public void active(String activeCode) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = " update user set state = 1 where activeCode = ?";
		qr.update(sql, activeCode);
	}
	public User findUserByUsernameAndPassword(String username, String password) throws UserException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from user where username = ? and password = ? ";
		User user = null;
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class),username,password);
			if(user==null)
				throw new UserException("登录失败");
			if(user.getState()!=1){
				throw new UserException("未激活");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public User modifyUser(User user) throws SQLException, UserException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = " update user set password = ? ,gender = ? , telephone = ? where username = ?";
		qr.update(sql, user.getPassword(),user.getGender(),user.getTelephone(),user.getUsername());
		User user2 = findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
		return user2;
	}

}
