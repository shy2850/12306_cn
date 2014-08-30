package com.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bean.dao.TrainDao;
import com.bean.po.Train;
import com.createJavaFile.Main.DBManager;
import com.createJavaFile.createModel.SqlColumn;
import com.createJavaFile.myutil.PropertyReader;
import com.createJavaFile.myutil.Util;
import com.wll7821.filter.WebContext;

public class TrainAction {
	private DBManager dbmanager = new DBManager();
	private Boolean show_sql = "true".equalsIgnoreCase(PropertyReader.get(Util.SHOW_SQL));
	
	private TrainDao trainDao;
	public void setTrainDao(TrainDao trainDao) {
		this.trainDao = trainDao;
	}
	
	private Train train;
	public void setTrain(Train train) {
		this.train = train;
	}
	
	private Integer fromStation;
	public void setFromStation(Integer fromStation) {
		this.fromStation = fromStation;
	}
	private Integer toStation;
	public void setToStation(Integer toStation) {
		this.toStation = toStation;
	}
	private Date beginDate;
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public String addTrain(){
		HttpServletRequest req = WebContext.getRequest();
		
		Calendar calendar = Calendar.getInstance(  );
		calendar.setTime( new Date() );
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		
		//由于更新车次信息轮训一小时，新增车次有可能在0-1点产生不同步数据，杜绝之。
//		if( hours <= 2 ){	
//			req.setAttribute("error", "凌晨0-2点之间禁止添加车次信息");
//			return "jsonp";
//		}
		
		
		if( null == train.getArrive_time() || null == train.getStart_time() ){
			req.setAttribute("error", "车次开始结束时间必填！");
			return "jsonp";
		}
		//计算并设置历时时间
		long lishiTime =train.getArrive_time().getTime() -  train.getStart_time().getTime();
		train.setLishi(  (int) (lishiTime / 1000 / 60)  );
		if( train.getLishi() < 0  ){
			req.setAttribute("error", "到达时间不得晚于起始时间");
			return "jsonp";
		}
		
		SqlColumn columnN = new SqlColumn("checi", train.getCheci());
		try {
			List<Train> lisTrains = trainDao.getTrains(columnN);
			if( lisTrains.size() > 0 ){
				req.setAttribute("error", "已经存在该车次");
				return "jsonp";
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		Connection connection = dbmanager.getConn();
		try {
			connection.setAutoCommit(false);	//开启事务
			train.setBegin_date(train.getStart_time());		
			train.setIs_model(true);
			trainDao.save(train);
			train.setIs_model(false);
			for (int i = 1; i < 20; i++) {
				train.setBegin_date( getDayAfter( train.getBegin_date(), 1 ) );
				train.setStart_time( getDayAfter( train.getStart_time(), 1 ) );
				train.setArrive_time(getDayAfter(train.getArrive_time(), 1));
				trainDao.save(train);
			}
			connection.commit();						//完成批量更新的提交
			req.setAttribute("success", true);
		} catch (Exception e) {		
			try {
				connection.rollback();			//如果有异常，全部回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			req.setAttribute("error", e);
		}
		return "jsonp";
	}
	/**
	 * 对指定日期增加1天时间
	 * @param time 
	 * @return
	 */
	private Date getDayAfter(Date time,int Days) {
		Calendar calendar = Calendar.getInstance(  );
		calendar.setTime( time );
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		return calendar.getTime();
	}
	/**
	 * 开启自动更新车票信息, 绑定在UserAction.isLogin上面触发
	 * @return
	 */
	private boolean freshBegin;
	public void autoRefresh(){
		if( this.freshBegin ) return;
		this.freshBegin = true;
		new Thread( new Runnable() {
			private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			public void run() {
				Date d = new Date();
				Date last = getDayAfter(d, 19); //直接查询最后一天的数据
				Date last2 = getDayAfter(d, 18); 
				SqlColumn dsc = new SqlColumn("begin_date", format.format(last));
				SqlColumn dsc2 = new SqlColumn("begin_date", format.format(last2));
				try {
					List<Train> list = trainDao.getTrains(dsc);
					if( list.size() == 0 ){
						list = trainDao.getTrains(dsc2);
						for (Train train : list) {
							train.setStart_time( getDayAfter( train.getStart_time(), 1 ) );
							train.setArrive_time(getDayAfter(train.getArrive_time(), 1));
							trainDao.save(train);
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep( 1000*60*60 );	//每小时检查更新一遍
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} ).start();
	}

	public String list(){
		HttpServletRequest req = WebContext.getRequest();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SqlColumn from = new SqlColumn("start_station", fromStation);
		SqlColumn to = new SqlColumn("end_station", toStation);
		SqlColumn begin = new SqlColumn("begin_date", format.format(beginDate));
		SqlColumn isModel = new SqlColumn("is_model", 0);
		SqlColumn orderBy = new SqlColumn(null,"start_time");
		try {
			List<Train> list = trainDao.getTrains( from,to,begin,isModel,orderBy );
			Date d = new Date();
			if( beginDate.before(d) ){	//如果查询日期在当前日期之前
				for (Train train : list) {
					if( train.getStart_time().before(d) ){	//如果查到了在现在之前出发的，直接移除掉
						list.remove(train);
					}
				}
			}
			req.setAttribute("info", list);
			req.setAttribute("success", true);
		} catch (SQLException e) {
			req.setAttribute("error", e);
		}
		return "jsonp";
	}
	/**
	 * 订票完成票数减一
	 * @param id
	 * @param tp
	 * @throws SQLException
	 */
	public void offer_ticket(Integer id, String tp) throws SQLException{
		dbmanager.executeUpdate("update train set "+tp+"="+tp+"-1 where id = "+id, show_sql );
	}
	/**
	 * 订票完成票数减一
	 * @param id
	 * @param tp
	 * @throws SQLException
	 */
	public void back_ticket(Integer id, String tp) throws SQLException{
		dbmanager.executeUpdate("update train set "+tp+"="+tp+"+1 where id = "+id, show_sql );
	}
}
