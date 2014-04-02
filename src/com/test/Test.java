package com.test;

import com.createJavaFile.connectionSource.DBAutoRun;

public class Test {

	public static void main(String[] args) {
//		DBAutoRun.setConfig(DBAutoRun.MYSQL, "localhost", "simple_ssh", "root", "");
		new DBAutoRun().autoRun();
	}
	
}
