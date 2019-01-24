package app;

import java.io.IOException;

import app.builders.HttpRequestBuilder;
import app.builders.HttpResponseBuilder;
import app.http_additions.HttpRequestReader;
import app.interfaces.HttpRequest;
import app.interfaces.HttpResponse;
import app.server.Server;

public class Main {

    public static void main(String[] args) throws IOException {
        httpParser();
    }

    private static void httpParser() throws IOException {
        HttpRequestReader httpRequestReader = new HttpRequestReader();
        String httpRequestString = httpRequestReader.readRequest();
        HttpRequest httpRequest = new HttpRequestBuilder(httpRequestString).createHttpRequest();
        HttpResponse httpResponse = new HttpResponseBuilder(httpRequest).createHttpResponse();

        System.out.println(new String(httpResponse.getBytes()));
    }
}