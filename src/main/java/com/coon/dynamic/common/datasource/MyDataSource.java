package com.coon.dynamic.common.datasource;

import com.coon.dynamic.common.enums.DatabaseTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义数据注解
 * 拥有aop切换数据源
 * @author Chen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MyDataSource {
	DatabaseTypeEnum value();
}
