package http.server;

import app.handler.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Server {

    private RequestHandler requestHandler;

    public Server(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    protected void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept(); // Wait for request
                new Thread(new ServerThread(socket, requestHandler)).start(); // Start new thread to handle request
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
