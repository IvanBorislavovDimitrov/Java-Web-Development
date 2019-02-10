package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpCookiesGetter {

    private static final String COOKIE = "Cookie";

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String requestLine = bufferedReader.readLine();
        Map<String, String> headers = getHeaders();
        Map<String, String> cookies = getCookies(headers);
        cookies.forEach((k, v) -> System.out.println(String.format("%s <-> %s", k, v)));
    }

    private static Map<String, String> getCookies(Map<String, String> headers) {
        if (headers.get(COOKIE) == null) {
            return Collections.emptyMap();
        }
        return Arrays.stream(headers.get(COOKIE).split(";\\s+"))
                .map(h -> h.split("="))
                .collect(Collectors.toMap(h -> h[0], h -> h[1], (o, n) -> o, LinkedHashMap::new));
    }


    private static Map<String, String> getHeaders() throws IOException {
        Map<String, String> headers = new LinkedHashMap<>();
        String line = bufferedReader.readLine();
        while (line != null && !line.isEmpty()) {
            String[] headerKeyValue = line.split(":\\s+");
            headers.put(headerKeyValue[0], headerKeyValue[1]);
            line = bufferedReader.readLine();
        }

        return headers;
    }
}
