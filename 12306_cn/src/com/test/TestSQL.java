package com.test;

import java.sql.SQLException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.createJavaFile.Main.DBManager;

public class TestSQL {
	
/**
 * @throws SQLException 
 * 	



 */
	
		public static void main(String[] args) throws SQLException {
			DBManager manager = new DBManager();
			
			String sql = "SELECT orders.*, train.checi, train.start_station, train.end_station, train.start_time, train.arrive_time, contact.`name` FROM orders,train,contact WHERE orders.uid = ? AND orders.status = ? AND orders.cid = contact.id AND orders.tid = train.id";
			
			List<Object> objects = manager.executeQuery(sql, true, null, 28);
			
			System.out.println(  JSON.toJSONString( objects ) );
		}
	
}
