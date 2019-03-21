package com.coon.dynamic.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.coon.dynamic.common.datasource.DynamicDataSource;
import com.coon.dynamic.common.enums.DatabaseTypeEnum;
import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author chenjl
 */
@Configuration
public class MyDataSourceConfig {
	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSourceDB1() { return getDataSource(DatabaseTypeEnum.SUMMER_DATABASE_1); }

	@Bean
	public DataSource dataSourceDB2() {
		return getDataSource(DatabaseTypeEnum.SUMMER_DATABASE_2);
	}

	@Bean
	public DataSource dataSourceDB3() {
		return getDataSource(DatabaseTypeEnum.SUMMER_DATABASE_3);
	}

	private DataSource getDataSource(DatabaseTypeEnum databaseTypeEnum) {
		DruidDataSource dataSource = new DruidDataSource();
		String prefix = "spring.datasource." + databaseTypeEnum.getValue();
		dataSource.setDriverClassName(environment.getProperty(prefix + ".driver-class-name"));
		dataSource.setUsername(environment.getProperty(prefix + ".username"));
		dataSource.setPassword(environment.getProperty(prefix + ".password"));
		dataSource.setUrl(environment.getProperty(prefix + ".url"));
		dataSource.setRemoveAbandonedTimeout(120);
		dataSource.setLogAbandoned(true);
		dataSource.setMaxActive(200);
		dataSource.setInitialSize(0);
		dataSource.setMinIdle(5);
		return dataSource;
	}

	/**
	 * 动态数据源配置
	 */
	@Bean
	@Primary
	public DataSource multipleDataSource(@Qualifier("dataSourceDB1") DataSource db1,
	                                     @Qualifier("dataSourceDB2") DataSource db2,
	                                     @Qualifier("dataSourceDB3") DataSource db3){
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		Map<Object, Object> targetDataSources = Maps.newHashMap();
		targetDataSources.put(DatabaseTypeEnum.SUMMER_DATABASE_1.getValue(), db1 );
		targetDataSources.put(DatabaseTypeEnum.SUMMER_DATABASE_2.getValue(), db2 );
		targetDataSources.put(DatabaseTypeEnum.SUMMER_DATABASE_3.getValue(), db3 );
		dynamicDataSource.setTargetDataSources(targetDataSources);
		dynamicDataSource.setDefaultTargetDataSource(db1);
		return dynamicDataSource;
	}

	@Bean("sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(multipleDataSource(dataSourceDB1(), dataSourceDB2(), dataSourceDB3()));
		MybatisConfiguration configuration = new MybatisConfiguration();
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setCacheEnabled(false);
		sqlSessionFactory.setConfiguration(configuration);
		return sqlSessionFactory.getObject();
	}

}
