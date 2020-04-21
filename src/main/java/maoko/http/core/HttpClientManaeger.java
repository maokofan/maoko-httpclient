package maoko.http.core;

import maoko.http.conf.HttpTdPoolConf;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 创建httpclient的builder池
 *
 * @author fanpei
 */
@Component
public class HttpClientManaeger {

    @Autowired
    HttpTdPoolConf conf;

    /**
     * 实例化连接池管理器
     *
     * @return
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getPoolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(conf.getMaxTotal());
        connManager.setDefaultMaxPerRoute(conf.getDefaultMaxPerRoute());
        return connManager;
    }

    /**
     * 实例化连接池
     *
     * @param connManager
     * @return
     */
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(
            @Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager connManager) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(connManager);
        return httpClientBuilder;
    }

    /**
     * 实例化httpclient连接
     *
     * @return
     */
    @Bean
    public CloseableHttpClient getCloseableHttpClient(
            @Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder) {
        return httpClientBuilder.build();
    }

    /**
     * builder是requestConfig内部类，通过custom获取一个builder对象爱你乖，设置builder的连接信息
     *
     * @return
     */
    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder() {
        RequestConfig.Builder builder = RequestConfig.custom();
        return builder.setConnectTimeout(conf.getConnectTimeout())
                .setConnectionRequestTimeout(conf.getConnectionRequestTimeout())
                .setSocketTimeout(conf.getMaxTotal());
        //fanpei 过时配置改为线程定时删除 2020.4.21
        //.setStaleConnectionCheckEnabled(conf.isStaleConnectionCheckEnabled());

    }

    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder) {
        return builder.build();
    }

}
