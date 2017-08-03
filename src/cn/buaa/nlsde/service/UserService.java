package cn.buaa.nlsde.service;

import java.sql.SQLException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import cn.buaa.nlsde.dao.UserDao;
import cn.buaa.nlsde.domain.User;
import cn.buaa.nlsde.exception.UserException;
import cn.buaa.nlsde.utils.SendJMail;

public class UserService {
	UserDao userDao = new UserDao();

	public void register(User user) throws UserException {
		boolean b = userDao.findUserByUsername(user.getUsername());
		if(b==true){
			throw new UserException("用户名已存在");
		}
		userDao.addUser(user);
		
		String emailMsg = "点击此处<a href='http://localhost/product/activeServlet?activeCode="+user.getActiveCode()+"'>完成注册";
		b = SendJMail.sendMail(user.getEmail(), emailMsg);
		if(!b){
			throw new UserException("注册邮件发送失败");
		}
		
	}

	public void active(String activeCode) throws SQLException {
		userDao.active(activeCode);
	}

	public User login(User user) throws UserException {
		user = userDao.findUserByUsernameAndPassword(user.getUsername(),
				user.getPassword());
		return user;
	}

	public User modifyUser(User user) throws SQLException, UserException {
		return userDao.modifyUser(user);
		
	}
}
