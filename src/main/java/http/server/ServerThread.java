package http.server;

import app.handler.AppRequestHandler;
import app.handler.RequestHandler;
import http.request.HttpMethod;
import http.request.Request;
import http.response.Response;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ServerThread implements Runnable {

    private Socket socket;
    private RequestHandler requestHandler;

    private final BufferedReader in;
    private final PrintWriter out;

    public ServerThread(Socket socket, RequestHandler requestHandler) {
        this.socket = socket;
        this.requestHandler = requestHandler;

        // Get I/O streams
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            String requestLine = in.readLine();
            System.out.println(requestLine);

            StringTokenizer stringTokenizer = new StringTokenizer(requestLine);
            HttpMethod method = HttpMethod.valueOf(stringTokenizer.nextToken());
            String path = stringTokenizer.nextToken();

            int contentLength = 0;
            String body = "";

            do {
                requestLine = in.readLine();
                if (requestLine.startsWith("Content-Length:")) {
                    contentLength = Integer.parseInt(requestLine.split("Content-Length: ")[1]);
                }
                System.out.println(requestLine);
            } while (!requestLine.trim().equals(""));

            if (method == HttpMethod.POST) {
                // Read request body
                if (contentLength > 0) {
                    char[] buffer = new char[contentLength];
                    in.read(buffer, 0, contentLength);
                    body = String.valueOf(buffer);
                    System.out.println(body);
                }
            }

            // Create request
            Request request = new Request(method, path, body);

            // Respond to request
            Response response = requestHandler.handle(request);

            // Send response
            out.println(response.getResponse());
            System.out.println("\n" + response.getResponse() + "\n");

            // Close connection
            socket.close();
            in.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
