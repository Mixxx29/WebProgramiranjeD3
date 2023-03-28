package http.response;

public class RedirectResponse extends Response {

    private String location;

    public RedirectResponse(String location) {
        this.location = location;
    }

    @Override
    public String getResponse() {
        return "HTTP/1.1 302 Moved Temporary\r\nLocation: " + location + "\r\n\r\n";
    }
}
