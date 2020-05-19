package cn.spring.ssm;

import cn.spring.ssm.configure.db.MasterDataSourcePrefix;
import cn.spring.ssm.configure.db.SlaveDataSourcePrefix;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableConfigurationProperties(value = {MasterDataSourcePrefix.class, SlaveDataSourcePrefix.class})
@EnableAsync
@EnableHystrix
@EnableHystrixDashboard
public class SsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsmApplication.class, args);
    }
}
