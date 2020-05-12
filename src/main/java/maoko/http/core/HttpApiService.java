package maoko.http.core;


import maoko.common.DateFormatUtil;
import maoko.http.conf.HttpPlatConf;
import maoko.http.core.entity.HttpCmdRequest;
import maoko.http.core.entity.HttpResult;
import maoko.http.core.entity.UrlParam;
import maoko.http.exception.HttpQueryException;
import org.apache.http.HttpRequest;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

/**
 * http客户端，get、post、put、delete请求
 */
@Service
public class HttpApiService {
    private static final String ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String HEADER_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36";
    private static final String HEADER_ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
    // private static final String HEADER_ACCEPT_LANGUAGE = "zh-CN,zh;q=0.9";

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private HttpPlatConf conf;

    /**
     * 向服务端发送请求并返回 body String
     *
     * @param params
     * @return
     * @throws Exception
     */
    public HttpResult sendRequest2Server(HttpCmdRequest params) throws Exception {
        if (params == null) {
            throw new Exception("http params is null");
        }

        HttpResult result = null;
        HttpUriRequest httpRequest = null;
        URI url = genURLWithParams(params);
        switch (params.getMethod()) {
            case GET:
                httpRequest = new HttpGet(url);
                break;
            case PUT:
                httpRequest = new HttpPut(url);
                break;
            case POST:
                httpRequest = new HttpPost(url);
                break;
            case DELETE:
                httpRequest = new HttpDeleteWithBody(url);
                break;
            default:
                break;
        }
        result = doRequest(httpRequest, params);
        return result;
    }

    /**
     * 发送http请求
     *
     * @param httpRequest
     * @param param
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     * @throws HttpQueryException
     */
    private HttpResult doRequest(HttpUriRequest httpRequest, HttpCmdRequest param)
            throws IOException, ClientProtocolException, HttpQueryException {
        HttpResult result = null;
        CloseableHttpResponse response = null;
        try {
            // 设置header信息
            // 指定报文头【Content-type】、【User-Agent】
            httpRequest.setHeader("Content-Type", CONTENT_TYPE);
            httpRequest.setHeader("User-Agent", HEADER_USER_AGENT);
            httpRequest.setHeader("Accept", HEADER_ACCEPT);
            // httpRequest.setHeader("Accept-Language", HEADER_ACCEPT_LANGUAGE);

            // 设置body请求参数
            if (param.getBody() != null && !"".equals(param.getBody())) {
                if (httpRequest instanceof HttpEntityEnclosingRequestBase) {
                    StringEntity body = new StringEntity(param.getBody(), ENCODING);
                    body.setContentType(CONTENT_TYPE);
                    body.setContentEncoding(ENCODING);
                    ((HttpEntityEnclosingRequestBase) httpRequest).setEntity(body);
                }
            }
            printRequest(param, httpRequest);
            // 执行请求操作，并拿到结果（同步阻塞）
            response = httpClient.execute(httpRequest);
            // 获取结果
            result = new HttpResult(response);
            printReponse(httpRequest, result);
            if (!result.isOK()) {
                throw new HttpQueryException(result.toString());
            }

        } finally {
            // 释放链接
            if (response != null) {
                response.close();
            }
        }
        return result;
    }

    /**
     * 获取URL
     *
     * @param param
     * @return
     * @throws ParseException
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws URISyntaxException
     */
    private URI genURLWithParams(HttpCmdRequest param)
            throws ParseException, UnsupportedEncodingException, IOException, URISyntaxException {
        // 组装完整url
        String url = new StringBuilder(conf.getServerURL()).append(param.getUrl()).toString();
        // 设置url请求参数
        URIBuilder builder = new URIBuilder(url);
        if (param.getParams() != null && !param.getParams().isEmpty()) {
            for (UrlParam e : param.getParams()) {
                builder.addParameter(e.getKey(), e.getValue());
            }
        }
        return builder.build();
    }

    /**
     * 打印请求
     *
     * @param param
     * @param httpRequest
     * @return
     * @throws IOException
     */
    private void printRequest(HttpCmdRequest param, HttpRequest httpRequest) throws IOException {
        StringBuilder requestSb = new StringBuilder();
        requestSb.append("<--发送请求信息，请求时间：").append(DateFormatUtil.dateformat(new Date()))
                .append(System.lineSeparator());
        requestSb.append("请求方法：").append(httpRequest.toString()).append(System.lineSeparator());
        if (httpRequest instanceof HttpEntityEnclosingRequestBase) {
            HttpEntityEnclosingRequestBase request = (HttpEntityEnclosingRequestBase) httpRequest;
            StringEntity entity = (StringEntity) request.getEntity();
            if (entity != null) {
                requestSb.append(System.lineSeparator());
                requestSb.append("请求详情:").append(entity.toString()).append(System.lineSeparator());
                requestSb.append("请求body:").append(param.getBody());
            }
        }
        System.out.println(requestSb.toString());
    }

    /**
     * 答应返回
     *
     * @param request
     * @param result
     * @return
     */
    private void printReponse(HttpUriRequest request, HttpResult result) {
        StringBuilder requestSb = new StringBuilder();
        requestSb.append("-->解析请求返回，返回时间：").append(DateFormatUtil.dateformat(new Date()))
                .append(System.lineSeparator());
        requestSb.append("请求地址：").append(request.getURI().toString()).append(System.lineSeparator());
        requestSb.append("返回详情:").append(result.toString()).append(System.lineSeparator());
        System.out.println(requestSb.toString());
    }
}
