package com.bupt.smarthome.ds;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class Druid {
	private static DataSource ds = null;

	/**
	 * Static block only executes once to initialise static variables upon class loading and is deleted after that
	 */
	static {
		try {
			Properties pro = new Properties();
			pro.load(Druid.class.getClassLoader().getResourceAsStream("druid.properties"));
			ds = DruidDataSourceFactory.createDataSource(pro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Getter for ds, static method accessed by class name
	 * @return DataSource Utilised by Spring JDBC template
	 */
	public static DataSource getDataSource () {
		return ds;
	}
}
