package com.coon.dynamic.common.datasource;

import com.coon.dynamic.common.enums.DatabaseTypeEnum;

/**
 * @author Chen
 */
public class DbContextHolder {
	private static final ThreadLocal contextHolder = new ThreadLocal<>();

	/**
	 * 设置数据源
	 */
	public static void setDatabaseType(DatabaseTypeEnum dbTypeEnum) {
		contextHolder.set(dbTypeEnum.getValue());
	}

	/**
	 * 取得当前数据源
	 */
	public static String getDatabaseType() {
		return (String) contextHolder.get();
	}

	/**
	 * 清除上下文数据
	 */
	public static void clearDatabaseType() {
		contextHolder.remove();
	}

}
