package com.bean.po;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.createJavaFile.createModel.ParseResultSetable;

public class Contact implements ParseResultSetable,Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer uid;
	private String name;
	private Integer sex;
	private String id_card;
	private String tel;
	private String email;
	private Integer type;
	public Contact(){}
	public Contact(Integer id, Integer uid, String name, Integer sex, String id_card, String tel, String email, Integer type){
		super();
		this.id = id;
		this.uid = uid;
		this.name = name;
		this.sex = sex;
		this.id_card = id_card;
		this.tel = tel;
		this.email = email;
		this.type = type;
		}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return id;
	}
	public void setUid(Integer uid){
		this.uid = uid;
	}
	public Integer getUid(){
		return uid;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setSex(Integer sex){
		this.sex = sex;
	}
	public Integer getSex(){
		return sex;
	}
	public void setId_card(String id_card){
		this.id_card = id_card;
	}
	public String getId_card(){
		return id_card;
	}
	public void setTel(String tel){
		this.tel = tel;
	}
	public String getTel(){
		return tel;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return email;
	}
	public void setType(Integer type){
		this.type = type;
	}
	public Integer getType(){
		return type;
	}
	@Override
	public boolean equals(Object o) {
		return (o instanceof Contact)&&((Contact)o).getId()==getId();
	}
	public Object parseOf(ResultSet rs) throws SQLException{
		if(null==rs)return null;
		Integer id = rs.getInt("id");
		Integer uid = rs.getInt("uid");
		String name = rs.getString("name");
		Integer sex = rs.getInt("sex");
		String id_card = rs.getString("id_card");
		String tel = rs.getString("tel");
		String email = rs.getString("email");
		Integer type = rs.getInt("type");
		Contact contact = new Contact(id, uid, name, sex, id_card, tel, email, type);
		return contact;
	}
}