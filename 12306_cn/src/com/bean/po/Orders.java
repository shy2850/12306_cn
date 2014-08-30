package com.bean.po;
import java.util.Date;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.createJavaFile.createModel.ParseResultSetable;

public class Orders implements ParseResultSetable,Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String order_no;
	private Integer uid;
	private Integer tid;
	private Integer cid;
	private Date order_date;
	private Float price;
	private String seat_type;
	private Integer status;
	public Orders(){}
	public Orders(Integer id, String order_no, Integer uid, Integer tid, Integer cid, Date order_date, Float price, String seat_type, Integer status){
		super();
		this.id = id;
		this.order_no = order_no;
		this.uid = uid;
		this.tid = tid;
		this.cid = cid;
		this.order_date = order_date;
		this.price = price;
		this.seat_type = seat_type;
		this.status = status;
		}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return id;
	}
	public void setOrder_no(String order_no){
		this.order_no = order_no;
	}
	public String getOrder_no(){
		return order_no;
	}
	public void setUid(Integer uid){
		this.uid = uid;
	}
	public Integer getUid(){
		return uid;
	}
	public void setTid(Integer tid){
		this.tid = tid;
	}
	public Integer getTid(){
		return tid;
	}
	public void setCid(Integer cid){
		this.cid = cid;
	}
	public Integer getCid(){
		return cid;
	}
	public void setOrder_date(Date order_date){
		this.order_date = order_date;
	}
	public Date getOrder_date(){
		return order_date;
	}
	public void setPrice(Float price){
		this.price = price;
	}
	public Float getPrice(){
		return price;
	}
	public void setSeat_type(String seat_type){
		this.seat_type = seat_type;
	}
	public String getSeat_type(){
		return seat_type;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public Integer getStatus(){
		return status;
	}
	@Override
	public boolean equals(Object o) {
		return (o instanceof Orders)&&((Orders)o).getId()==getId();
	}
	public Object parseOf(ResultSet rs) throws SQLException{
		if(null==rs)return null;
		Integer id = rs.getInt("id");
		String order_no = rs.getString("order_no");
		Integer uid = rs.getInt("uid");
		Integer tid = rs.getInt("tid");
		Integer cid = rs.getInt("cid");
		Date order_date = rs.getTimestamp("order_date");
		Float price = rs.getFloat("price");
		String seat_type = rs.getString("seat_type");
		Integer status = rs.getInt("status");
		Orders orders = new Orders(id, order_no, uid, tid, cid, order_date, price, seat_type, status);
		return orders;
	}
}