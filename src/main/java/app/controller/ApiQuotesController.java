package app.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import http.request.Request;
import http.response.HtmlResponse;
import http.response.JSONResponse;
import http.response.Response;
import model.Quote;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ApiQuotesController  extends Controller {

    public ApiQuotesController(Request request) {
        super(request);
    }

    @Override
    public Response doGet() {
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

        return new JSONResponse(builder.toString());
    }

    @Override
    public Response doPost() {
        System.out.println(request.getBody());
        StringTokenizer tokenizer = new StringTokenizer(request.getBody(), "&");
        String author = tokenizer.nextToken().split("=")[1].replace("+", " ");
        String quoteText = tokenizer.nextToken().split("=")[1]
                .replace("+", " ")
                .replace("%21", "!");

        // Create new quote
        Quote quote = new Quote(author, quoteText);

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

        // Add new quote
        quotes.add(quote);

        // Save data
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/java/quotes.txt"))) {
            String read;
            writer.write(gson.toJson(quotes));
        } catch (IOException e) {
            //e.printStackTrace();
            return new HtmlResponse(500);
        }

        return new HtmlResponse(200);
    }
}
