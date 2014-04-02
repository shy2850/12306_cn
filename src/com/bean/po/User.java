package com.bean.po;
import java.util.Date;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.createJavaFile.createModel.ParseResultSetable;

public class User implements ParseResultSetable,Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String password;
	private String email;
	private String id_card;
	private String from;
	private Date birth;
	private Boolean sex;
	private Integer type;
	public User(){}
	public User(Integer id, String name, String password, String email, String id_card, String from, Date birth, Boolean sex, Integer type){
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.id_card = id_card;
		this.from = from;
		this.birth = birth;
		this.sex = sex;
		this.type = type;
		}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return email;
	}
	public void setId_card(String id_card){
		this.id_card = id_card;
	}
	public String getId_card(){
		return id_card;
	}
	public void setFrom(String from){
		this.from = from;
	}
	public String getFrom(){
		return from;
	}
	public void setBirth(Date birth){
		this.birth = birth;
	}
	public Date getBirth(){
		return birth;
	}
	public void setSex(Boolean sex){
		this.sex = sex;
	}
	public Boolean getSex(){
		return sex;
	}
	public void setType(Integer type){
		this.type = type;
	}
	public Integer getType(){
		return type;
	}
	@Override
	public boolean equals(Object o) {
		return (o instanceof User)&&((User)o).getId()==getId();
	}
	public Object parseOf(ResultSet rs) throws SQLException{
		if(null==rs)return null;
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String password = rs.getString("password");
		String email = rs.getString("email");
		String id_card = rs.getString("id_card");
		String from = rs.getString("from");
		Date birth = rs.getDate("birth");
		Boolean sex = rs.getBoolean("sex");
		Integer type = rs.getInt("type");
		User user = new User(id, name, password, email, id_card, from, birth, sex, type);
		return user;
	}
}