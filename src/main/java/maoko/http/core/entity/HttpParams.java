package maoko.http.core.entity;

import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * http参数基类
 *
 * @author fanpei
 */
public abstract class HttpParams {
    protected HttpMethod method;// 方法      -无需params手动设置
    private List<UrlParam> params;// 地址参数
    private String body; // body信息-json格式

    /**
     * 默认构造函数
     */
    public HttpParams() {
        this.method = HttpMethod.GET;
        //this.searchParams = new SearchParam();
    }

    /**
     * @param method http方法
     * @param params url参数
     * @param body   body JSON字符串格式参数
     */
    public HttpParams(HttpMethod method, List<UrlParam> params, String body) {
        //this.searchParams = new SearchParam();
        this.method = method;
        this.params = params;
        this.body = body;
    }

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
     * 新增地址参数
     *
     * @param key
     * @param value
     */
    public void addUrlParam(String key, String value) {
        if (params == null)
            params = new ArrayList<>();
        params.add(new UrlParam(key, value));
    }

}
