package maoko.http.core.entity;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 自定义状态返回结果
 *
 * @author fanpei
 */
public class HttpResult {
    private static final String ENCODING = "utf-8";
    /**
     * 服务端响应码
     */
    private int code;
    /**
     * 服务端响应信息
     */
    private String body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public HttpResult(HttpResponse response) throws ParseException, IOException {
        this.code = response.getStatusLine().getStatusCode();
        this.body = EntityUtils.toString(response.getEntity(), ENCODING);
    }

    public HttpResult(HttpResponse response, String encoding) throws ParseException, IOException {
        this.code = response.getStatusLine().getStatusCode();
        this.body = EntityUtils.toString(response.getEntity(), encoding);
    }

    public boolean isOK() {
        return code - 200 >= 0 && code - 200 < 100;
    }

    @Override
    public String toString() {
        String result = null;
        StringBuilder r = new StringBuilder();
        r.append("statusCode:").append(code);
        r.append(System.lineSeparator());
        r.append("body:").append(this.body);
        result = r.toString();
        return result;
    }
}
