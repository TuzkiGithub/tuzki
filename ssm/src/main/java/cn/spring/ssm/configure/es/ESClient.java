package cn.spring.ssm.configure.es;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
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

    private  ArrayList<HttpHost> hostList;

    @Value("${elasticsearch.schema}")
    private  String schema; // 使用的协议

    @Value("${elasticsearch.connectTimeOut}")
    private  int connectTimeOut; // 连接超时时间

    @Value("${elasticsearch.socketTimeOut}")
    private  int socketTimeOut; // 连接超时时间

    @Value("${elasticsearch.connectionRequestTimeOut}")
    private  int connectionRequestTimeOut; // 获取连接的超时时间

    @Value("${elasticsearch.maxConnectNum}")
    private  int maxConnectNum; // 最大连接数

    @Value("${elasticsearch.maxConnectPerRoute}")
    private  int maxConnectPerRoute; // 最大路由连接数

    @Value("${elasticsearch.cluster.host}")
    private String hosts;

    @Value("${elasticsearch.cluster.port}")
    private String ports;

    private void init() {
        hostList = new ArrayList<>();
        String[] lport = ports.split(",");
        String[] lhost = hosts.split(",");
        int nodeNum = lport.length;
        for (int i = 0; i < nodeNum; i++) {
            hostList.add(new HttpHost(lhost[i], Integer.parseInt(lport[i]), schema));
        }
        log.info("elasticsearch node info: {}", hostList.toString());
    }



    @Bean
    public RestHighLevelClient esClient() {
        init();
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
