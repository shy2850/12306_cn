package com.test;

import java.sql.SQLException;

import com.alibaba.fastjson.JSON;
import com.bean.dao.UserDao;
import com.bean.po.User;

public class TestFastJson {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		UserDao dao = new UserDao();
		
		User user = dao.getUsers().get(0);
		
		String userStr = JSON.toJSONString(user);
		
		System.out.println(userStr);

	}

}
