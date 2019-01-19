package app.server;

import app.builders.HttpRequestBuilder;
import app.builders.HttpResponseBuilder;
import app.interfaces.HttpRequest;
import app.interfaces.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;

    private int port;

    private List<String> validUrls;

    public Server(int port, List<String> validUrls) throws IOException {
        this.port = port;
        this.validUrls = validUrls;
        serverSocket = new ServerSocket(port);
    }

    public void run() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = bufferedReader.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null && line.length() > 0) {
                sb.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }

            HttpRequest httpRequest = new HttpRequestBuilder(sb.toString()).createHttpRequest();

            HttpResponse httpResponse = new HttpResponseBuilder(httpRequest, validUrls).createHttpResponse();

            socket.getOutputStream().write(httpResponse.getBytes());

            socket.close();
        }
    }
}