package app.handler;

import http.request.Request;
import http.response.Response;

public abstract class RequestHandler {
    public  abstract Response handle(Request request);
}
