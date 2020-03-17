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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
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
@MapperScan(basePackages = {"cn.spring.ssm.dao.common","cn.spring.ssm.dao.es"}, sqlSessionTemplateRef = "commonSqlSessionTemplate")
public class CommonDataSourceConfig {
    @Bean(name = "commonDataSource")
    @ConfigurationProperties(prefix = "mysql.datasource.common")
    public DataSource getDateSource() {
        return DataSourceBuilder.create().build();
    }

    //创建SqlSessionFactory
    @Bean(name = "commonSqlSessionFactory")
    public SqlSessionFactory commonSqlSessionFactory(@Qualifier("commonDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    //创建事务管理器
    @Bean(name = "commonTransactionManager")
    public DataSourceTransactionManager commonTransactionManager(@Qualifier("commonDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //创建SqlSessionTemplate
    @Bean(name = "commonSqlSessionTemplate")
    public SqlSessionTemplate commonSqlSessionTemplate(@Qualifier("commonSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
