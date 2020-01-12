package cn.spring.ssm.configure.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.configure.db
 * User: 25414
 * Date: 2019/9/16
 * Time: 17:26
 * Description:
 */
@Configuration
@MapperScan(basePackages = "cn.spring.ssm.dao.integral", sqlSessionTemplateRef = "integralSqlSessionTemplate")
public class IntegralDataSourceConfig {
    @Bean(name = "integralDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.integral")
    public DataSource getDateSource() {
        return DataSourceBuilder.create().build();
    }

    //创建SqlSessionFactory
    @Bean(name = "integralSqlSessionFactory")
    public SqlSessionFactory integralSqlSessionFactory(@Qualifier("integralDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    //创建事务管理器
    @Bean(name = "integralTransactionManager")
    public DataSourceTransactionManager integralTransactionManager(@Qualifier("integralDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //创建SqlSessionTemplate
    @Bean(name = "integralSqlSessionTemplate")
    public SqlSessionTemplate integralSqlSessionTemplate(@Qualifier("integralSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
