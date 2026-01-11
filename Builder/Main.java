package Builder;

import java.util.HashMap;
import java.util.Map;

class HttpRequest {
    // Required
    private final String url;

    // Optional
    private final String method;
    private final Map<String, String> headers;
    private final Map<String, String> queryParams;
    private final String body;
    private final int timeout;

    public HttpRequest(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers;
        this.queryParams = builder.queryParams;
        this.body = builder.body;
        this.timeout = builder.timeout;
    }

    @Override
    public String toString() {
        return "HttpRequest [url=" + url + ", method=" + method + ", headers=" + headers + ", queryParams="
                + queryParams + ", body=" + body + ", timeout=" + timeout + "]";
    }

}

class Builder {
    public final String url; // required
    public String method = "GET";
    public Map<String, String> headers = new HashMap<>();
    public Map<String, String> queryParams = new HashMap<>();
    public String body;
    public int timeout = 30000;

    public Builder(String url) {
        this.url = url;
    }

    public Builder addMethod(String method){
        this.method = method;
        return this;
    }

    public Builder addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public Builder addQueryParam(String key, String value) {
        this.queryParams.put(key, value);
        return this;
    }

    public Builder body(String body) {
        this.body = body;
        return this;
    }

    public Builder timeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public HttpRequest build() {
        return new HttpRequest(this);
    }
}

public class Main {
    public static void main(String[] args) {
        HttpRequest req1 = new Builder("www.acb.com").addMethod("GET").body("{\"key\":\"value\"}").build();
        System.out.println(req1.toString());
    }
}
