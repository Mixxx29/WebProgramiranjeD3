package http.response;

public class HtmlResponse extends Response {

    private String html;
    private int code;

    public HtmlResponse(String html) {
        this.html = html;
        code = 200;
    }

    public HtmlResponse(int code) {
        this.code = code;
    }

    @Override
    public String getResponse() {
        switch (code) {
            case 200 -> {
                return "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n" + html;
            }

            case 404 -> {
                return "HTTP/1.1 404 Not Found\r\n\r\n";
            }

            case 500 -> {
                return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
            }
        }

        return "HTTP/1.1 500 Internal Server Error\r\n\r\n";
    }
}
