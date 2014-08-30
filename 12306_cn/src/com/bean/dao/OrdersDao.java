package com.bean.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.createJavaFile.Main.DBManager;
import com.createJavaFile.createModel.ParseResultSetable;
import com.createJavaFile.createModel.SqlColumn;
import com.createJavaFile.myutil.PropertyReader;
import com.createJavaFile.myutil.Util;
import com.bean.po.Orders;

public class OrdersDao {

	private DBManager dbmanager = new DBManager();
	private Boolean show_sql = "true".equalsIgnoreCase(PropertyReader.get(Util.SHOW_SQL));

	private List<Orders> findOrders(SqlColumn...sqlColumns)throws SQLException{
		ParseResultSetable orders = new Orders();
		StringBuffer sql = new StringBuffer("select * from orders where 1=1 ");
		for (int i = 0; i < sqlColumns.length; i++) {
			SqlColumn s = sqlColumns[i];
		if(null != s.getColumnName()){
				if(null == s.getColumnValue())sql.append("and "+s.getColumnName()+" is null ");
				else if("".equals(s.getColumnValue()))sql.append("and "+s.getColumnName()+" is not null ");
				else {
					if(s.isExist())sql.append("and "+s.getColumnName()+" like '%"+s.getColumnValue()+"%' ");
					else  sql.append("and "+s.getColumnName()+" != '%"+s.getColumnValue()+"%' ");
				}
			}
			if(i==sqlColumns.length-1&&null==s.getColumnName())sql.append("order by "+s.getColumnValue());
		}
		List<Object> list = dbmanager.executeQuery(sql.toString(), show_sql, orders);
		List<Orders> ordersList = new ArrayList<Orders>();
		for(int i=0;i<list.size();i++){
			ordersList.add((Orders)list.get(i));
			}
		return ordersList;
	}//findOrders()

	/** 模糊查询实现得到列表
	  * @param sqlColumns 传入查询的参数对象，最后一组参数若name属性为空，将value的toString作为order_by依据
	  * @return   返回一组对象列表
	  * @throws SQLException 可能抛出SQL异常
	  */
	public List<Orders> findOrderss(SqlColumn...sqlColumns)throws SQLException{
		return findOrders(sqlColumns);
	}

	private List<Orders> getOrders(SqlColumn...sqlColumns)throws SQLException{
		ParseResultSetable orders = new Orders();
		StringBuffer sql = new StringBuffer("select * from orders where 1=1 ");
		for (int i = 0; i < sqlColumns.length; i++) {
			SqlColumn s = sqlColumns[i];
		if(null != s.getColumnName()){
				if(null == s.getColumnValue())sql.append("and "+s.getColumnName()+" is null ");
				else {
					if(s.isExist())sql.append("and "+s.getColumnName()+" = '"+s.getColumnValue()+"' ");
					else  sql.append("and "+s.getColumnName()+" != '"+s.getColumnValue()+"' ");
				}
			}
			if(i==sqlColumns.length-1&&null==s.getColumnName())sql.append("order by "+s.getColumnValue());
		}
		List<Object> list = dbmanager.executeQuery(sql.toString(), show_sql, orders);
		List<Orders> ordersList = new ArrayList<Orders>();
		for(int i=0;i<list.size();i++){
			ordersList.add((Orders)list.get(i));
			}
		return ordersList;
	}//getOrders()

	/** 精确查询实现得到列表
	  * @param sqlColumns 传入查询的参数对象，最后一组参数若name属性为空，将value的toString作为order_by依据
	  * @return   返回一组对象列表
	  * @throws SQLException 可能抛出SQL异常
	  */
	public List<Orders> getOrderss(SqlColumn...sqlColumns)throws SQLException{
		return getOrders(sqlColumns);
	}

	public int save(Orders orders) throws SQLException{
		return dbmanager.executeUpdate("insert into orders values(?,?,?,?,?,?,?,?,?)", show_sql, orders.getId(),orders.getOrder_no(),orders.getUid(),orders.getTid(),orders.getCid(),orders.getOrder_date(),orders.getPrice(),orders.getSeat_type(),orders.getStatus());
	}//save()

	public int delete(SqlColumn...sqlColumns)throws SQLException{
		StringBuffer sql = new StringBuffer("delete from orders where 1=1 ");
		for (int i = 0; i < sqlColumns.length; i++) {
			SqlColumn s = sqlColumns[i];
			if(null!=s.getColumnValue()&&!"".equals(s.getColumnValue())){
				if(s.isExist())sql.append("and "+s.getColumnName()+" = '"+s.getColumnValue()+"' ");
				else sql.append("and "+s.getColumnName()+" != '"+s.getColumnValue()+"' ");
			}
		}
		return dbmanager.executeUpdate(sql.toString(), show_sql);
	}

	public Orders getOrdersByPK(Integer id) throws SQLException{
		List<Orders> list = getOrders(new SqlColumn("id",id));
		if(list.size() == 0)return null;
		else return list.get(0);
	}

	public int deleteByPK(Integer id) throws SQLException{
		String sql = new String("delete from orders where id = " + id);
		return dbmanager.executeUpdate(sql, show_sql);
	}
	public int update(Orders orders) throws SQLException{
		return dbmanager.executeUpdate("update orders set order_no=?,uid=?,tid=?,cid=?,order_date=?,price=?,seat_type=?,status=? where id=?", show_sql, orders.getOrder_no(),orders.getUid(),orders.getTid(),orders.getCid(),orders.getOrder_date(),orders.getPrice(),orders.getSeat_type(),orders.getStatus(),orders.getId());
	}//update()

}//class OrdersDao