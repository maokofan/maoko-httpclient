package maoko.http.core;

import org.apache.http.client.methods.HttpPost;

import java.net.URI;

public class HttpDeleteWithBody extends HttpPost {

    public HttpDeleteWithBody(URI url) {
        super(url);
    }

    @Override
    public String getMethod() {
        return "DELETE";
    }

}
