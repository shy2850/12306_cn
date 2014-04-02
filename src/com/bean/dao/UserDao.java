package com.bean.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.createJavaFile.Main.DBManager;
import com.createJavaFile.createModel.ParseResultSetable;
import com.createJavaFile.createModel.SqlColumn;
import com.bean.po.User;
import com.createJavaFile.myutil.PropertyReader;
import com.createJavaFile.myutil.Util;

public class UserDao{

	private DBManager dbmanager = new DBManager();
	private Boolean show_sql = "true".equalsIgnoreCase( PropertyReader.get(Util.SHOW_SQL) );

	private List<User> findUser(SqlColumn...sqlColumns)throws SQLException{
		ParseResultSetable user = new User();
		StringBuffer sql = new StringBuffer("select * from user where 1=1 ");
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
		List<Object> list = dbmanager.executeQuery(sql.toString(), show_sql, user);
		List<User> userList = new ArrayList<User>();
		for(int i=0;i<list.size();i++){
			userList.add((User)list.get(i));
			}
		return userList;
	}//findUser()

	/** 模糊查询实现得到列表
	  * @param sqlColumns 传入查询的参数对象，最后一组参数若name属性为空，将value的toString作为order_by依据
	  * @return   返回一组对象列表
	  * @throws SQLException 可能抛出SQL异常
	  */
	public List<User> findUsers(SqlColumn...sqlColumns)throws SQLException{
		return findUser(sqlColumns);
	}

	private List<User> getUser(SqlColumn...sqlColumns)throws SQLException{
		ParseResultSetable user = new User();
		StringBuffer sql = new StringBuffer("select * from user where 1=1 ");
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
		List<Object> list = dbmanager.executeQuery(sql.toString(), show_sql, user);
		List<User> userList = new ArrayList<User>();
		for(int i=0;i<list.size();i++){
			userList.add((User)list.get(i));
			}
		return userList;
	}//getUser()

	/** 精确查询实现得到列表
	  * @param sqlColumns 传入查询的参数对象，最后一组参数若name属性为空，将value的toString作为order_by依据
	  * @return   返回一组对象列表
	  * @throws SQLException 可能抛出SQL异常
	  */
	public List<User> getUsers(SqlColumn...sqlColumns)throws SQLException{
		return getUser(sqlColumns);
	}

	public int save(User user) throws SQLException{
		return dbmanager.executeUpdate("insert into user values(?,?,?,?,?,?,?,?,?)", show_sql, user.getId(),user.getName(),user.getPassword(),user.getEmail(),user.getId_card(),user.getFrom(),user.getBirth(),user.getSex(),user.getType());
	}//save()

	public int delete(SqlColumn...sqlColumns)throws SQLException{
		StringBuffer sql = new StringBuffer("delete from user where 1=1 ");
		for (int i = 0; i < sqlColumns.length; i++) {
			SqlColumn s = sqlColumns[i];
			if(null!=s.getColumnValue()&&!"".equals(s.getColumnValue())){
				if(s.isExist())sql.append("and "+s.getColumnName()+" = '"+s.getColumnValue()+"' ");
				else sql.append("and "+s.getColumnName()+" != '"+s.getColumnValue()+"' ");
			}
		}
		return dbmanager.executeUpdate(sql.toString(), show_sql);
	}

	public User getUserByPK(Integer id) throws SQLException{
		List<User> list = getUser(new SqlColumn("id",id));
		if(list.size() == 0)return null;
		else return list.get(0);
	}

	public int deleteByPK(Integer id) throws SQLException{
		String sql = new String("delete from user where id = " + id);
		return dbmanager.executeUpdate(sql, show_sql);
	}
	public int update(User user) throws SQLException{
		return dbmanager.executeUpdate("update user set name=?,password=?,email=?,id_card=?,from=?,birth=?,sex=?,type=? where id=?", show_sql, user.getName(),user.getPassword(),user.getEmail(),user.getId_card(),user.getFrom(),user.getBirth(),user.getSex(),user.getType(),user.getId());
	}//update()

}//class UserDao