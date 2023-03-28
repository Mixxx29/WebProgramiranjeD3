package app.handler;

import app.Utils;
import app.controller.AppQuotesController;
import http.request.HttpMethod;
import http.request.Request;
import http.response.HtmlResponse;
import http.response.JSONResponse;
import http.response.RedirectResponse;
import http.response.Response;

public class AppRequestHandler extends RequestHandler {

    @Override
    public Response handle(Request request) {
        if (request.getPath().equals("/quotes") && request.getHttpMethod() == HttpMethod.GET) {
            return new AppQuotesController(request).doGet();
        }

        if (request.getPath().equals("/save-quote") && request.getHttpMethod() == HttpMethod.POST) {
            String response = Utils.post("127.0.0.1:8081/quotes", request.getBody()); // Get response
            if (response == null) return new HtmlResponse(500);
            return new RedirectResponse("http://127.0.0.1:8080/quotes");
        }

        if (request.getPath().equals("/api/quotes") && request.getHttpMethod() == HttpMethod.GET) {
            String response = Utils.get("127.0.0.1:8081/quotes"); // Get response
            if (response == null) return new HtmlResponse(500);
            String json = response.split("\n\n")[1]; // Get json
            return new JSONResponse(json);
        }

        if (request.getPath().equals("/api/qod") && request.getHttpMethod() == HttpMethod.GET) {
            String response = Utils.get("127.0.0.1:8081/qod"); // Get response
            if (response == null) return new HtmlResponse(500);
            String json = response.split("\n\n")[1]; // Get json
            return new JSONResponse(json);
        }

        return new HtmlResponse(404);
    }
}
