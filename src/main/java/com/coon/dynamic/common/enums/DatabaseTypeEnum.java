package com.coon.dynamic.common.enums;

/**
 * @author Chen
 */
public enum DatabaseTypeEnum {
	/**
	 *  数据库隐射枚举
	 */
	SUMMER_DATABASE_1("db1"),
	SUMMER_DATABASE_2("db2"),
	SUMMER_DATABASE_3("db3");

	private String value;

	DatabaseTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
