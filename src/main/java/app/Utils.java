package app;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class Utils {

    public static String get(String path) {
        StringTokenizer stringTokenizer = new StringTokenizer(path, ":/"); // Split path
        String host = stringTokenizer.nextToken(); // Get host
        int port = Integer.parseInt(stringTokenizer.nextToken()); // Get port
        String route = stringTokenizer.nextToken(); // Get route

        String response = null;
        try (Socket socket = new Socket(host, port)) { // Connect
            // Get I/O streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            // Create request string
            String request = "GET /" + route + " HTTP/1.1\r\n\r\n";

            // Send request
            out.println(request);

            // Receive response string
            StringBuilder builder = new StringBuilder();
            String readLine;
            while ((readLine = in.readLine()) != null) {
                builder.append(readLine).append("\n");
            }

            response = builder.toString();

            // Close connection
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public static String post(String path, String body) {
        StringTokenizer stringTokenizer = new StringTokenizer(path, ":/"); // Split path
        String host = stringTokenizer.nextToken(); // Get host
        int port = Integer.parseInt(stringTokenizer.nextToken()); // Get port
        String route = stringTokenizer.nextToken(); // Get route

        String response = null;
        try (Socket socket = new Socket(host, port)) { // Connect
            // Get I/O streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            // Create request string
            String request = "POST /" +
                    route +
                    " HTTP/1.1\r\n\"Content-Type: application/json\r\n" +
                    "Content-Length: " + body.length() + "\r\n\r\n" + body;

            // Send request
            out.println(request);

            // Receive response string
            StringBuilder builder = new StringBuilder();
            String readLine;
            while ((readLine = in.readLine()) != null) {
                builder.append(readLine).append("\n");
            }

            response = builder.toString();

            // Close connection
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
