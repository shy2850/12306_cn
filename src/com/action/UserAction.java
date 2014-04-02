package com.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bean.dao.UserDao;
import com.bean.po.User;
import com.createJavaFile.createModel.SqlColumn;
import com.wll7821.filter.WebContext;

public class UserAction{
	
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	private User user;
	public void setUser(User user) {
		this.user = user;
	}
	private String password;
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String register(){
		
		HttpServletRequest req = WebContext.getRequest();
		
		if( user.getPassword().equals(password) ){
			
			try {
				userDao.save(user);
				return "login";
			} catch (SQLException e) {
				req.setAttribute("error", e);
				return "register";
			}
			
		}else{
			req.setAttribute("error", "两次输入的密码不一致");
			return "register";
		}
		
	}

	public String login(){
		HttpServletRequest req = WebContext.getRequest();
		HttpSession session = WebContext.getSession();
		Object sessionUser = null;
		if( user.getName().length() > 0 && user.getPassword().length() > 0 ){
			SqlColumn nameColumn = new SqlColumn("name", user.getName());
			SqlColumn passwordColumn = new SqlColumn("password", user.getPassword());
			try {
				List<?> list = userDao.getUsers(nameColumn,passwordColumn);
				sessionUser = list.size() > 0 ? list.get(0) : null;
			} catch (SQLException e) {
				
			}
			if( null != sessionUser ){
				session.setAttribute("loginUser", sessionUser);
				return "index";
			}else{
				req.setAttribute("error", "用户名或者密码错误！");
				return "relogin";
			}
			
		}else{
			req.setAttribute("error", "需要填写完整的用户名和密码");
			return "relogin";
		}
		
	}
	
}
