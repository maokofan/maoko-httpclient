package maoko.http.core.entity;

import org.apache.http.Header;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * http命令基类
 *
 * @author fanpei
 */
public abstract class HttpCmdRequest {
    /**
     * 方法  -无需params手动设置
     */
    private HttpMethod method;
    /**
     * 头
     */
    private List<Header> headers;
    /**
     * 地址参数
     */
    private List<UrlParam> params;
    /**
     * body信息-json格式
     */
    private String body;


    /**
     * 获取接口URL
     *
     * @return
     */
    public abstract String getUrl();//获取接口URL

    /**
     * 获取http方法
     *
     * @return
     */
    public abstract HttpMethod getMethod();

    /**
     * 获取地址发送参数
     *
     * @return
     */
    public abstract List<UrlParam> getParams();

    /**
     * 获取body发送参数
     *
     * @return
     */
    public abstract String getBody();

    /**
     * 默认构造函数
     */
    public HttpCmdRequest() {
        this.method = HttpMethod.GET;
    }

    /**
     * @param method http方法
     * @param params url参数
     * @param body   body JSON字符串格式参数
     */
    public HttpCmdRequest(HttpMethod method, List<UrlParam> params, String body) {
        this.method = method;
        this.params = params;
        this.body = body;
    }

    /**
     * 新增地址参数
     *
     * @param key
     * @param value
     */
    public void addUrlParam(String key, String value) {
        if (params == null) {
            params = new LinkedList<>();
        }
        params.add(new UrlParam(key, value));
    }

    /**
     * 添加头
     *
     * @param header
     */
    public void addHeader(Header header) {
        if (headers == null) {
            headers = new LinkedList<>();
        }
        headers.add(header);
    }

    public void setBody(String body) {
        this.body = body;
    }
}
