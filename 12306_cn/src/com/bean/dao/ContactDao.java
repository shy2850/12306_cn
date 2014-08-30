package com.bean.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.createJavaFile.Main.DBManager;
import com.createJavaFile.createModel.ParseResultSetable;
import com.createJavaFile.createModel.SqlColumn;
import com.createJavaFile.myutil.PropertyReader;
import com.createJavaFile.myutil.Util;
import com.bean.po.Contact;

public class ContactDao {

	private DBManager dbmanager = new DBManager();
	private Boolean show_sql = "true".equalsIgnoreCase(PropertyReader.get(Util.SHOW_SQL));

	private List<Contact> findContact(SqlColumn...sqlColumns)throws SQLException{
		ParseResultSetable contact = new Contact();
		StringBuffer sql = new StringBuffer("select * from contact where 1=1 ");
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
		List<Object> list = dbmanager.executeQuery(sql.toString(), show_sql, contact);
		List<Contact> contactList = new ArrayList<Contact>();
		for(int i=0;i<list.size();i++){
			contactList.add((Contact)list.get(i));
			}
		return contactList;
	}//findContact()

	/** 模糊查询实现得到列表
	  * @param sqlColumns 传入查询的参数对象，最后一组参数若name属性为空，将value的toString作为order_by依据
	  * @return   返回一组对象列表
	  * @throws SQLException 可能抛出SQL异常
	  */
	public List<Contact> findContacts(SqlColumn...sqlColumns)throws SQLException{
		return findContact(sqlColumns);
	}

	private List<Contact> getContact(SqlColumn...sqlColumns)throws SQLException{
		ParseResultSetable contact = new Contact();
		StringBuffer sql = new StringBuffer("select * from contact where 1=1 ");
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
		List<Object> list = dbmanager.executeQuery(sql.toString(), show_sql, contact);
		List<Contact> contactList = new ArrayList<Contact>();
		for(int i=0;i<list.size();i++){
			contactList.add((Contact)list.get(i));
			}
		return contactList;
	}//getContact()

	/** 精确查询实现得到列表
	  * @param sqlColumns 传入查询的参数对象，最后一组参数若name属性为空，将value的toString作为order_by依据
	  * @return   返回一组对象列表
	  * @throws SQLException 可能抛出SQL异常
	  */
	public List<Contact> getContacts(SqlColumn...sqlColumns)throws SQLException{
		return getContact(sqlColumns);
	}

	public int save(Contact contact) throws SQLException{
		return dbmanager.executeUpdate("insert into contact values(?,?,?,?,?,?,?,?)", show_sql, contact.getId(),contact.getUid(),contact.getName(),contact.getSex(),contact.getId_card(),contact.getTel(),contact.getEmail(),contact.getType());
	}//save()

	public int delete(SqlColumn...sqlColumns)throws SQLException{
		StringBuffer sql = new StringBuffer("delete from contact where 1=1 ");
		for (int i = 0; i < sqlColumns.length; i++) {
			SqlColumn s = sqlColumns[i];
			if(null!=s.getColumnValue()&&!"".equals(s.getColumnValue())){
				if(s.isExist())sql.append("and "+s.getColumnName()+" = '"+s.getColumnValue()+"' ");
				else sql.append("and "+s.getColumnName()+" != '"+s.getColumnValue()+"' ");
			}
		}
		return dbmanager.executeUpdate(sql.toString(), show_sql);
	}

	public Contact getContactByPK(Integer id) throws SQLException{
		List<Contact> list = getContact(new SqlColumn("id",id));
		if(list.size() == 0)return null;
		else return list.get(0);
	}

	public int deleteByPK(Integer id) throws SQLException{
		String sql = new String("delete from contact where id = " + id);
		return dbmanager.executeUpdate(sql, show_sql);
	}
	public int update(Contact contact) throws SQLException{
		return dbmanager.executeUpdate("update contact set uid=?,name=?,sex=?,id_card=?,tel=?,email=?,type=? where id=?", show_sql, contact.getUid(),contact.getName(),contact.getSex(),contact.getId_card(),contact.getTel(),contact.getEmail(),contact.getType(),contact.getId());
	}//update()

}//class ContactDao