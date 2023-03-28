package app.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import http.request.Request;
import http.response.HtmlResponse;
import http.response.JSONResponse;
import http.response.Response;
import model.Quote;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QODController extends Controller {

    public QODController(Request request) {
        super(request);
    }

    @Override
    public Response doGet() {
        // Load data
        Gson gson = new Gson(); // Create gson object
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/quotes.txt"))) {
            String read;
            while ((read = reader.readLine()) != null) {
                builder.append(read);
            }
        } catch (IOException e) {
            //e.printStackTrace();
            return new HtmlResponse(500);
        }

        Type type = new TypeToken<ArrayList<Quote>>() {}.getType();
        List<Quote> quotes = gson.fromJson(builder.toString(), type);

        // Get random quote
        Random rand = new Random();
        String json = gson.toJson(quotes.get(rand.nextInt(quotes.size())));

        return new JSONResponse(json);
    }

    @Override
    public Response doPost() {
        return null;
    }
}
