package cn.spring.ssm.configure.es;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * Package: cn.spring.ssm.configure
 * User: 25414
 * Date: 2020/2/28
 * Time: 16:14
 * Description:
 */
@Slf4j
@Configuration
public class ESClient {
    private static final String schema = "http"; // 使用的协议
    private static ArrayList<HttpHost> hostList = null;

    private static int connectTimeOut = 1000; // 连接超时时间
    private static int socketTimeOut = 30000; // 连接超时时间
    private static int connectionRequestTimeOut = 500; // 获取连接的超时时间

    private static int maxConnectNum = 100; // 最大连接数
    private static int maxConnectPerRoute = 100; // 最大路由连接数

    static {
        hostList = new ArrayList<>();
        hostList.add(new HttpHost("127.0.0.1", 9200, schema));
        hostList.add(new HttpHost("127.0.0.1", 9201, schema));
        hostList.add(new HttpHost("127.0.0.1", 9202, schema));

    }

    @Bean
    public RestHighLevelClient esClient() {
        log.info(hostList.toString());
        RestClientBuilder builder = RestClient.builder(hostList.toArray(new HttpHost[0]));
        // 异步httpclient连接延时配置
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeOut);
            requestConfigBuilder.setSocketTimeout(socketTimeOut);
            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
            return requestConfigBuilder;
        });
        // 异步httpclient连接数配置
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(maxConnectNum);
            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
            return httpClientBuilder;
        });
        return new RestHighLevelClient(builder);
    }

}
