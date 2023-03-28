package http.request;

public class Request {
    private final HttpMethod httpMethod;
    private final String path;

    private final String body;

    public Request(HttpMethod requestMethod, String path) {
        this.httpMethod = requestMethod;
        this.path = path;
        this.body = "";
    }

    public Request(HttpMethod requestMethod, String path, String body) {
        this.httpMethod = requestMethod;
        this.path = path;
        this.body = body;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public String getBody() {
        return body;
    }
}
