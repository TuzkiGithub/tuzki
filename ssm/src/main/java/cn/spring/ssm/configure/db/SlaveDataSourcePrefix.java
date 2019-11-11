package cn.spring.ssm.configure.db;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.configure.db
 * User: 25414
 * Date: 2019/9/16
 * Time: 14:26
 * Description:
 */
@Data
@ConfigurationProperties(prefix = "mysql.datasource.slave")
public class SlaveDataSourcePrefix {
    private String url;
    private String username;
    private String password;
    private int minPoolSize;
    private int maxPoolSize;
    private int maxLifetime;
    private int borrowConnectionTimeout;
    private int loginTimeout;
    private int maintenanceInterval;
    private int maxIdleTime;
    private String testQuery;
}
