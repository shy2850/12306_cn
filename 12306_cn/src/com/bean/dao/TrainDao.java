package com.bean.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.createJavaFile.Main.DBManager;
import com.createJavaFile.createModel.ParseResultSetable;
import com.createJavaFile.createModel.SqlColumn;
import com.createJavaFile.myutil.PropertyReader;
import com.createJavaFile.myutil.Util;
import com.bean.po.Train;

public class TrainDao {

	private DBManager dbmanager = new DBManager();
	private Boolean show_sql = "true".equalsIgnoreCase(PropertyReader.get(Util.SHOW_SQL));

	private List<Train> findTrain(SqlColumn...sqlColumns)throws SQLException{
		ParseResultSetable train = new Train();
		StringBuffer sql = new StringBuffer("select * from train where 1=1 ");
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
		List<Object> list = dbmanager.executeQuery(sql.toString(), show_sql, train);
		List<Train> trainList = new ArrayList<Train>();
		for(int i=0;i<list.size();i++){
			trainList.add((Train)list.get(i));
			}
		return trainList;
	}//findTrain()

	/** 模糊查询实现得到列表
	  * @param sqlColumns 传入查询的参数对象，最后一组参数若name属性为空，将value的toString作为order_by依据
	  * @return   返回一组对象列表
	  * @throws SQLException 可能抛出SQL异常
	  */
	public List<Train> findTrains(SqlColumn...sqlColumns)throws SQLException{
		return findTrain(sqlColumns);
	}

	private List<Train> getTrain(SqlColumn...sqlColumns)throws SQLException{
		ParseResultSetable train = new Train();
		StringBuffer sql = new StringBuffer("select * from train where 1=1 ");
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
		List<Object> list = dbmanager.executeQuery(sql.toString(), show_sql, train);
		List<Train> trainList = new ArrayList<Train>();
		for(int i=0;i<list.size();i++){
			trainList.add((Train)list.get(i));
			}
		return trainList;
	}//getTrain()

	/** 精确查询实现得到列表
	  * @param sqlColumns 传入查询的参数对象，最后一组参数若name属性为空，将value的toString作为order_by依据
	  * @return   返回一组对象列表
	  * @throws SQLException 可能抛出SQL异常
	  */
	public List<Train> getTrains(SqlColumn...sqlColumns)throws SQLException{
		return getTrain(sqlColumns);
	}

	public int save(Train train) throws SQLException{
		return dbmanager.executeUpdate("insert into train values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", show_sql, train.getId(),train.getStart_station(),train.getEnd_station(),train.getBegin_date(),train.getStart_time(),train.getArrive_time(),train.getLishi(),train.getCheci(),train.getSwz_num(),train.getTd_num(),train.getYd_num(),train.getEd_num(),train.getGr_num(),train.getRw_num(),train.getYw_num(),train.getRz_num(),train.getYz_num(),train.getWz_num(),train.getOther(),train.getSwz_price(),train.getTd_price(),train.getYd_price(),train.getEd_price(),train.getGr_price(),train.getRw_price(),train.getYw_price(),train.getRz_price(),train.getYz_price(),train.getWz_price(),train.getType(),train.getIs_model());
	}//save()

	public int delete(SqlColumn...sqlColumns)throws SQLException{
		StringBuffer sql = new StringBuffer("delete from train where 1=1 ");
		for (int i = 0; i < sqlColumns.length; i++) {
			SqlColumn s = sqlColumns[i];
			if(null!=s.getColumnValue()&&!"".equals(s.getColumnValue())){
				if(s.isExist())sql.append("and "+s.getColumnName()+" = '"+s.getColumnValue()+"' ");
				else sql.append("and "+s.getColumnName()+" != '"+s.getColumnValue()+"' ");
			}
		}
		return dbmanager.executeUpdate(sql.toString(), show_sql);
	}

	public Train getTrainByPK(Integer id) throws SQLException{
		List<Train> list = getTrain(new SqlColumn("id",id));
		if(list.size() == 0)return null;
		else return list.get(0);
	}

	public int deleteByPK(Integer id) throws SQLException{
		String sql = new String("delete from train where id = " + id);
		return dbmanager.executeUpdate(sql, show_sql);
	}
	public int update(Train train) throws SQLException{
		return dbmanager.executeUpdate("update train set start_station=?,end_station=?,begin_date=?,start_time=?,arrive_time=?,lishi=?,checi=?,swz_num=?,td_num=?,yd_num=?,ed_num=?,gr_num=?,rw_num=?,yw_num=?,rz_num=?,yz_num=?,wz_num=?,other=?,swz_price=?,td_price=?,yd_price=?,ed_price=?,gr_price=?,rw_price=?,yw_price=?,rz_price=?,yz_price=?,wz_price=?,type=?,is_model=? where id=?", show_sql, train.getStart_station(),train.getEnd_station(),train.getBegin_date(),train.getStart_time(),train.getArrive_time(),train.getLishi(),train.getCheci(),train.getSwz_num(),train.getTd_num(),train.getYd_num(),train.getEd_num(),train.getGr_num(),train.getRw_num(),train.getYw_num(),train.getRz_num(),train.getYz_num(),train.getWz_num(),train.getOther(),train.getSwz_price(),train.getTd_price(),train.getYd_price(),train.getEd_price(),train.getGr_price(),train.getRw_price(),train.getYw_price(),train.getRz_price(),train.getYz_price(),train.getWz_price(),train.getType(),train.getIs_model(),train.getId());
	}//update()

}//class TrainDao