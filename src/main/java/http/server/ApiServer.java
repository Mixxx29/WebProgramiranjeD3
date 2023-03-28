package http.server;

import app.handler.ApiRequestHandler;
import app.handler.AppRequestHandler;
import app.handler.RequestHandler;

public class ApiServer extends Server {

    public ApiServer(RequestHandler requestHandler) {
        super(requestHandler);
    }

    public static void main(String[] args) {
        new ApiServer(new ApiRequestHandler()).start(8081);
    }
}
