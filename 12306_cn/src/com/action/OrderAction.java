package com.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bean.dao.OrdersDao;
import com.bean.po.Orders;
import com.bean.po.User;
import com.createJavaFile.Main.DBManager;
import com.createJavaFile.myutil.PropertyReader;
import com.createJavaFile.myutil.Util;
import com.wll7821.filter.WebContext;

public class OrderAction {
	private DBManager dbmanager = new DBManager();
	private Boolean show_sql = "true".equalsIgnoreCase(PropertyReader.get(Util.SHOW_SQL));
	
	private Orders orders;
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	private OrdersDao ordersDao;
	public void setOrdersDao(OrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}
	private TrainAction trainAction;
	public void setTrainAction(TrainAction trainAction) {
		this.trainAction = trainAction;
	}
	
	private Integer status;
	public void setStatus(Integer status) {
		this.status = status;
	}
	private Integer id;
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String addOrder(){
		HttpServletRequest request = WebContext.getRequest();
		HttpSession session = WebContext.getSession();
		User u = (User) session.getAttribute("user");
		
		orders.setUid(u.getId());
		orders.setOrder_no( "E" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + u.getId() );
		orders.setOrder_date(new Date());
		try {
			ordersDao.save(orders);
			trainAction.offer_ticket(orders.getTid(), orders.getSeat_type());
			request.setAttribute("success", true);
			request.setAttribute("info", orders);
		} catch (SQLException e) {
			request.setAttribute("error", e);
		}
		
		return "jsonp";
	}
	
	public String list(){
		String sql = "SELECT orders.*, train.checi, train.start_station, train.end_station, train.start_time, train.arrive_time, contact.`name` FROM orders,train,contact WHERE orders.uid = ? AND orders.status = ? AND orders.cid = contact.id AND orders.tid = train.id";
		HttpServletRequest request = WebContext.getRequest();
		HttpSession session = WebContext.getSession();
		User u = (User) session.getAttribute("user");
		
		List<Object> objects;
		try {
			objects = dbmanager.executeQuery(sql, show_sql, null, u.getId(),status);
			request.setAttribute("info", objects);
			request.setAttribute("success", true);
		} catch (SQLException e) {
			request.setAttribute("error", e);
		}
		
		return "jsonp";
	}
	
	public String pay(){
		HttpServletRequest request = WebContext.getRequest();
		HttpSession session = WebContext.getSession();
		User u = (User) session.getAttribute("user");
		
		try {
			Orders order = ordersDao.getOrdersByPK(id);
			if( !order.getUid().equals(u.getId()) ){
				throw new Exception("您没有权限进行此操作");
			}
			order.setStatus(1);
			ordersDao.update(order);
			request.setAttribute("success", true);
		} catch (Exception e) {
			request.setAttribute("error", e);
		}
		return "jsonp";
	}
	
	public String delete(){
		HttpServletRequest request = WebContext.getRequest();
		HttpSession session = WebContext.getSession();
		User u = (User) session.getAttribute("user");
		
		try {
			Orders order = ordersDao.getOrdersByPK(id);
			if( !order.getUid().equals(u.getId()) ){
				throw new Exception("您没有权限进行此操作");
			}
			ordersDao.deleteByPK(id);
			trainAction.back_ticket(order.getTid(), order.getSeat_type());
			request.setAttribute("success", true);
		} catch (Exception e) {
			request.setAttribute("error", e);
		}
		return "jsonp";
	}
	
}
