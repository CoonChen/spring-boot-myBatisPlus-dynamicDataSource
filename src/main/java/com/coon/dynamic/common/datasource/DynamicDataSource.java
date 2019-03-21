package com.coon.dynamic.common.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Chen
 */
public class DynamicDataSource  extends AbstractRoutingDataSource {

	/**
	 * 获取当前使用那个数据源
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DbContextHolder.getDatabaseType();
	}

}
