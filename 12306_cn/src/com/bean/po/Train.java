package com.bean.po;
import java.util.Date;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.createJavaFile.createModel.ParseResultSetable;

public class Train implements ParseResultSetable,Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer start_station;
	private Integer end_station;
	private Date begin_date;
	private Date start_time;
	private Date arrive_time;
	private Integer lishi;
	private String checi;
	private Integer swz_num;
	private Integer td_num;
	private Integer yd_num;
	private Integer ed_num;
	private Integer gr_num;
	private Integer rw_num;
	private Integer yw_num;
	private Integer rz_num;
	private Integer yz_num;
	private Integer wz_num;
	private String other;
	private Float swz_price;
	private Float td_price;
	private Float yd_price;
	private Float ed_price;
	private Float gr_price;
	private Float rw_price;
	private Float yw_price;
	private Float rz_price;
	private Float yz_price;
	private Float wz_price;
	private Integer type;
	private Boolean is_model;
	public Train(){}
	public Train(Integer id, Integer start_station, Integer end_station, Date begin_date, Date start_time, Date arrive_time, Integer lishi, String checi, Integer swz_num, Integer td_num, Integer yd_num, Integer ed_num, Integer gr_num, Integer rw_num, Integer yw_num, Integer rz_num, Integer yz_num, Integer wz_num, String other, Float swz_price, Float td_price, Float yd_price, Float ed_price, Float gr_price, Float rw_price, Float yw_price, Float rz_price, Float yz_price, Float wz_price, Integer type, Boolean is_model){
		super();
		this.id = id;
		this.start_station = start_station;
		this.end_station = end_station;
		this.begin_date = begin_date;
		this.start_time = start_time;
		this.arrive_time = arrive_time;
		this.lishi = lishi;
		this.checi = checi;
		this.swz_num = swz_num;
		this.td_num = td_num;
		this.yd_num = yd_num;
		this.ed_num = ed_num;
		this.gr_num = gr_num;
		this.rw_num = rw_num;
		this.yw_num = yw_num;
		this.rz_num = rz_num;
		this.yz_num = yz_num;
		this.wz_num = wz_num;
		this.other = other;
		this.swz_price = swz_price;
		this.td_price = td_price;
		this.yd_price = yd_price;
		this.ed_price = ed_price;
		this.gr_price = gr_price;
		this.rw_price = rw_price;
		this.yw_price = yw_price;
		this.rz_price = rz_price;
		this.yz_price = yz_price;
		this.wz_price = wz_price;
		this.type = type;
		this.is_model = is_model;
		}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return id;
	}
	public void setStart_station(Integer start_station){
		this.start_station = start_station;
	}
	public Integer getStart_station(){
		return start_station;
	}
	public void setEnd_station(Integer end_station){
		this.end_station = end_station;
	}
	public Integer getEnd_station(){
		return end_station;
	}
	public void setBegin_date(Date begin_date){
		this.begin_date = begin_date;
	}
	public Date getBegin_date(){
		return begin_date;
	}
	public void setStart_time(Date start_time){
		this.start_time = start_time;
	}
	public Date getStart_time(){
		return start_time;
	}
	public void setArrive_time(Date arrive_time){
		this.arrive_time = arrive_time;
	}
	public Date getArrive_time(){
		return arrive_time;
	}
	public void setLishi(Integer lishi){
		this.lishi = lishi;
	}
	public Integer getLishi(){
		return lishi;
	}
	public void setCheci(String checi){
		this.checi = checi;
	}
	public String getCheci(){
		return checi;
	}
	public void setSwz_num(Integer swz_num){
		this.swz_num = swz_num;
	}
	public Integer getSwz_num(){
		return swz_num;
	}
	public void setTd_num(Integer td_num){
		this.td_num = td_num;
	}
	public Integer getTd_num(){
		return td_num;
	}
	public void setYd_num(Integer yd_num){
		this.yd_num = yd_num;
	}
	public Integer getYd_num(){
		return yd_num;
	}
	public void setEd_num(Integer ed_num){
		this.ed_num = ed_num;
	}
	public Integer getEd_num(){
		return ed_num;
	}
	public void setGr_num(Integer gr_num){
		this.gr_num = gr_num;
	}
	public Integer getGr_num(){
		return gr_num;
	}
	public void setRw_num(Integer rw_num){
		this.rw_num = rw_num;
	}
	public Integer getRw_num(){
		return rw_num;
	}
	public void setYw_num(Integer yw_num){
		this.yw_num = yw_num;
	}
	public Integer getYw_num(){
		return yw_num;
	}
	public void setRz_num(Integer rz_num){
		this.rz_num = rz_num;
	}
	public Integer getRz_num(){
		return rz_num;
	}
	public void setYz_num(Integer yz_num){
		this.yz_num = yz_num;
	}
	public Integer getYz_num(){
		return yz_num;
	}
	public void setWz_num(Integer wz_num){
		this.wz_num = wz_num;
	}
	public Integer getWz_num(){
		return wz_num;
	}
	public void setOther(String other){
		this.other = other;
	}
	public String getOther(){
		return other;
	}
	public void setSwz_price(Float swz_price){
		this.swz_price = swz_price;
	}
	public Float getSwz_price(){
		return swz_price;
	}
	public void setTd_price(Float td_price){
		this.td_price = td_price;
	}
	public Float getTd_price(){
		return td_price;
	}
	public void setYd_price(Float yd_price){
		this.yd_price = yd_price;
	}
	public Float getYd_price(){
		return yd_price;
	}
	public void setEd_price(Float ed_price){
		this.ed_price = ed_price;
	}
	public Float getEd_price(){
		return ed_price;
	}
	public void setGr_price(Float gr_price){
		this.gr_price = gr_price;
	}
	public Float getGr_price(){
		return gr_price;
	}
	public void setRw_price(Float rw_price){
		this.rw_price = rw_price;
	}
	public Float getRw_price(){
		return rw_price;
	}
	public void setYw_price(Float yw_price){
		this.yw_price = yw_price;
	}
	public Float getYw_price(){
		return yw_price;
	}
	public void setRz_price(Float rz_price){
		this.rz_price = rz_price;
	}
	public Float getRz_price(){
		return rz_price;
	}
	public void setYz_price(Float yz_price){
		this.yz_price = yz_price;
	}
	public Float getYz_price(){
		return yz_price;
	}
	public void setWz_price(Float wz_price){
		this.wz_price = wz_price;
	}
	public Float getWz_price(){
		return wz_price;
	}
	public void setType(Integer type){
		this.type = type;
	}
	public Integer getType(){
		return type;
	}
	public void setIs_model(Boolean is_model){
		this.is_model = is_model;
	}
	public Boolean getIs_model(){
		return is_model;
	}
	@Override
	public boolean equals(Object o) {
		return (o instanceof Train)&&((Train)o).getId()==getId();
	}
	public Object parseOf(ResultSet rs) throws SQLException{
		if(null==rs)return null;
		Integer id = rs.getInt("id");
		Integer start_station = rs.getInt("start_station");
		Integer end_station = rs.getInt("end_station");
		Date begin_date = rs.getTimestamp("begin_date");
		Date start_time = rs.getTimestamp("start_time");
		Date arrive_time = rs.getTimestamp("arrive_time");
		Integer lishi = rs.getInt("lishi");
		String checi = rs.getString("checi");
		Integer swz_num = rs.getInt("swz_num");
		Integer td_num = rs.getInt("td_num");
		Integer yd_num = rs.getInt("yd_num");
		Integer ed_num = rs.getInt("ed_num");
		Integer gr_num = rs.getInt("gr_num");
		Integer rw_num = rs.getInt("rw_num");
		Integer yw_num = rs.getInt("yw_num");
		Integer rz_num = rs.getInt("rz_num");
		Integer yz_num = rs.getInt("yz_num");
		Integer wz_num = rs.getInt("wz_num");
		String other = rs.getString("other");
		Float swz_price = rs.getFloat("swz_price");
		Float td_price = rs.getFloat("td_price");
		Float yd_price = rs.getFloat("yd_price");
		Float ed_price = rs.getFloat("ed_price");
		Float gr_price = rs.getFloat("gr_price");
		Float rw_price = rs.getFloat("rw_price");
		Float yw_price = rs.getFloat("yw_price");
		Float rz_price = rs.getFloat("rz_price");
		Float yz_price = rs.getFloat("yz_price");
		Float wz_price = rs.getFloat("wz_price");
		Integer type = rs.getInt("type");
		Boolean is_model = rs.getBoolean("is_model");
		Train train = new Train(id, start_station, end_station, begin_date, start_time, arrive_time, lishi, checi, swz_num, td_num, yd_num, ed_num, gr_num, rw_num, yw_num, rz_num, yz_num, wz_num, other, swz_price, td_price, yd_price, ed_price, gr_price, rw_price, yw_price, rz_price, yz_price, wz_price, type, is_model);
		return train;
	}
}