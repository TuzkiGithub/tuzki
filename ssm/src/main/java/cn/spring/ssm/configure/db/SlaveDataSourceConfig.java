package cn.spring.ssm.configure.db;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.configure
 * User: 25414
 * Date: 2019/9/12
 * Time: 13:42
 * Description:
 */
@Configuration
@MapperScan(basePackages = "cn.spring.ssm.dao.slave", sqlSessionTemplateRef = "slaveSqlSessionTemplate")
public class SlaveDataSourceConfig {

    //创建数据源
    @Bean(name = "slaveDataSource")
    public DataSource getSlaveDataSource(SlaveDataSourcePrefix slaveDataSource) throws SQLException {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(slaveDataSource.getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setUser(slaveDataSource.getUsername());
        mysqlXADataSource.setPassword(slaveDataSource.getPassword());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);


        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("slaveDataSource");
        atomikosDataSourceBean.setMinPoolSize(slaveDataSource.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(slaveDataSource.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(slaveDataSource.getMaxLifetime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(slaveDataSource.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setLoginTimeout(slaveDataSource.getLoginTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(slaveDataSource.getMaintenanceInterval());
        atomikosDataSourceBean.setMaxIdleTime(slaveDataSource.getMaxIdleTime());
        atomikosDataSourceBean.setTestQuery(slaveDataSource.getTestQuery());
        return atomikosDataSourceBean;
    }

    //创建SqlSessionFactory
    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }


    //创建SqlSessionTemplate
    @Bean(name = "slaveSqlSessionTemplate")
    public SqlSessionTemplate slaveSqlSessionTemplate(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
