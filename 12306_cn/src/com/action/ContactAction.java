package com.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bean.dao.ContactDao;
import com.bean.po.Contact;
import com.bean.po.User;
import com.createJavaFile.createModel.SqlColumn;
import com.wll7821.filter.WebContext;

public class ContactAction {
	
	private ContactDao contactDao;
	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
	
	private Contact contact;
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	private Integer id;
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String list(){
		HttpServletRequest request = WebContext.getRequest();
		HttpSession session = WebContext.getSession();
		User user = (User) session.getAttribute("user");
		try {
			SqlColumn column = new SqlColumn("uid", user.getId());
			List<Contact> list = contactDao.getContacts(column);
			request.setAttribute("info", list);
			request.setAttribute("success", true);
		} catch (Exception e) {
			request.setAttribute("error", e);
		}
		
		return "jsonp";
	}
	
	public String add(){
		HttpServletRequest request = WebContext.getRequest();
		HttpSession session = WebContext.getSession();
		User user = (User) session.getAttribute("user");
		if( null == user ){
			request.setAttribute("error", "需要登录才能增加联系人");
			return "jsonp";
		}
		
		contact.setUid(user.getId());
		contact.setType(0);
		try {
			contactDao.save(contact);
			request.setAttribute("success", true);
		} catch (SQLException e) {
			request.setAttribute("error", e);
		}
		return "jsonp";
	}
	
	public String delete(){
		HttpServletRequest request = WebContext.getRequest();
		HttpSession session = WebContext.getSession();
		User user = (User) session.getAttribute("user");
		if( null == user ){
			request.setAttribute("error", "需要登录才能删除联系人");
			return "jsonp";
		}
		try {
			contactDao.deleteByPK(id);
			request.setAttribute("success", true);
		} catch (SQLException e) {
			request.setAttribute("error", e);
		}
		return "jsonp";
	}
	
}
