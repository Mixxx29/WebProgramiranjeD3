package app.handler;

import app.controller.ApiQuotesController;
import app.controller.QODController;
import http.request.HttpMethod;
import http.request.Request;
import http.response.HtmlResponse;
import http.response.Response;

public class ApiRequestHandler extends RequestHandler {
    @Override
    public Response handle(Request request) {
        if (request.getPath().equals("/quotes") && request.getHttpMethod() == HttpMethod.GET) {
            return new ApiQuotesController(request).doGet();
        }

        if (request.getPath().equals("/quotes") && request.getHttpMethod() == HttpMethod.POST) {
            return new ApiQuotesController(request).doPost();
        }

        if (request.getPath().equals("/qod") && request.getHttpMethod() == HttpMethod.GET) {
            return new QODController(request).doGet();
        }

        return new HtmlResponse(404);
    }
}
