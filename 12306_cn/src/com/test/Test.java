package com.test;

import java.sql.SQLException;
import java.util.List;

import com.bean.dao.TrainDao;
import com.bean.po.Train;
import com.createJavaFile.connectionSource.DBAutoRun;

public class Test {
	public static void main(String[] args) throws SQLException {
//		DBAutoRun.setConfig(DBAutoRun.MYSQL, "localhost", "simple_ssh", "root", "root");
		new DBAutoRun().autoRun();
		
//		TrainDao dao = new TrainDao();
//		List<Train> list = dao.getTrains();
//		for (Train train : list) {
//			train.setBegin_date(train.getStart_time());
//			dao.update(train);
//		}
		
	}
}
