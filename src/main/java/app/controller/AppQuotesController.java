package app.controller;

import http.request.Request;
import http.response.HtmlResponse;
import http.response.Response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AppQuotesController extends Controller {

    public AppQuotesController(Request request) {
        super(request);
    }

    @Override
    public Response doGet() {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/html/quotes.html"))) {
            String read;
            while ((read = reader.readLine()) != null) {
                builder.append(read).append("\n");
            }
        } catch (IOException e) {
            //e.printStackTrace();
            return new HtmlResponse(500);
        }

        return new HtmlResponse(builder.toString());
    }

    @Override
    public Response doPost() {
        return null;
    }
}
