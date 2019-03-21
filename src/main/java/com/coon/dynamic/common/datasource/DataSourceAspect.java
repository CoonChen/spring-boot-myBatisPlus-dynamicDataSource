package com.coon.dynamic.common.datasource;

import com.coon.dynamic.common.enums.DatabaseTypeEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Chen
 */

@Component
@Aspect
@Order(-100)
public class DataSourceAspect {
	private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

	@Pointcut("@annotation(com.coon.dynamic.common.datasource.MyDataSource)")
	private void databaseAspect() {}

	@Before("databaseAspect()")
	public void initDataSource(JoinPoint joinPoint) {

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		MyDataSource dataSource = methodSignature.getMethod().getAnnotation(MyDataSource.class);
		if (Objects.isNull(dataSource)) {
			logger.info("开始切换数据源.....");
			DbContextHolder.setDatabaseType(DatabaseTypeEnum.SUMMER_DATABASE_1);
			logger.info("已经切换到数据源：" + DatabaseTypeEnum.SUMMER_DATABASE_1.getValue());
		} else {
			DatabaseTypeEnum databaseTypeEnum = dataSource.value();
			logger.info("开始切换数据源.....");
			DbContextHolder.setDatabaseType(databaseTypeEnum);
			logger.info("已经切换到数据源：" + databaseTypeEnum.getValue());
		}
	}

	@After("databaseAspect()")
	public void destroyDataSource() {
		String dataSource = DbContextHolder.getDatabaseType();
		DbContextHolder.clearDatabaseType();
		logger.info("销毁数据源：" + dataSource);
	}

}
