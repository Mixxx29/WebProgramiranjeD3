package http.server;

import app.handler.AppRequestHandler;
import app.handler.RequestHandler;

public class AppServer extends Server {

    public AppServer(RequestHandler requestHandler) {
        super(requestHandler);
    }

    public static void main(String[] args) {
        new AppServer(new AppRequestHandler()).start(8080);
    }
}
