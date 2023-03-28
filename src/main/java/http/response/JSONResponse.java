package http.response;

public class JSONResponse extends Response{

    private String json;

    public JSONResponse(String json) {
        this.json = json;
    }

    @Override
    public String getResponse() {
        return "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "Access-Control-Allow-Origin: http://localhost:8080\r\n" +
                "Content-Length: " + json.length() + "\r\n\r\n" + json;
    }
}
