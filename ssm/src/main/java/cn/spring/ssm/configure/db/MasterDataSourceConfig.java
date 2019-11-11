package cn.spring.ssm.configure.db;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.jta.JtaTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = "cn.spring.ssm.dao.master", sqlSessionTemplateRef = "masterSqlSessionTemplate")
public class MasterDataSourceConfig {

    //创建数据源
    @Primary
    @Bean(name = "masterDataSource")
    public DataSource getMasterDataSource(MasterDataSourcePrefix masterDataSource) throws SQLException {

        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(masterDataSource.getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setUser(masterDataSource.getUsername());
        mysqlXADataSource.setPassword(masterDataSource.getPassword());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("masterDataSource");
        atomikosDataSourceBean.setMinPoolSize(masterDataSource.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(masterDataSource.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(masterDataSource.getMaxLifetime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(masterDataSource.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setLoginTimeout(masterDataSource.getLoginTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(masterDataSource.getMaintenanceInterval());
        atomikosDataSourceBean.setMaxIdleTime(masterDataSource.getMaxIdleTime());
        atomikosDataSourceBean.setTestQuery(masterDataSource.getTestQuery());
        return atomikosDataSourceBean;
    }

    //创建SqlSessionFactory
    @Primary
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }


    //创建SqlSessionTemplate
    @Primary
    @Bean(name = "masterSqlSessionTemplate")
    public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    @Bean(name = "masterTransactionManager")
    @Primary
    public JtaTransactionManager regTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }
}