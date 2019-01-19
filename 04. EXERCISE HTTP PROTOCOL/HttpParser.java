package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HttpParser {

	private static final int HTTP_STATUS_CODE_NOT_FOUND = 404;

	private static final int HTTP_STATUS_CODE_UNAUTHORIZED = 401;

	private static final int HTTP_STATUS_CODE_OK = 200;

	private static final int HTTP_STATUS_CODE_BAD_REQUEST = 400;

	private static final String HTTP_OK = "OK";

	private static final String HTTP_NOT_FOUND = "Not Found";

	private static final String HTTP_UNAUTHORIZED = "Unauthorized";

	private static final String HTTP_BAD_REQUEST = "Bad Request";

	private static final String NOT_FOUND = "The requested functionality was not found.";

	private static final String UNAUTHORIZED = "You are not authorized to access the requested functionality.";

	private static final String BAD_REQUEST = "There was an error with the requested functionality due to malformed request.";

	private static final String HOST = "Host";

	private static final String CONTENT_TYPE = "Content-Type";

	private static final String AUTHORIZATION = "Authorization";

	private static final String DATE = "Date";

	private static final String HTTP1 = "HTTP/1.1";

	private static List<String> validUrls = new ArrayList<>();

	private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		readUrls();
		String httpRequestString = readRequest();
		HttpRequest httpRequest = parseRequest(httpRequestString);
		HttpResponse httpResponse = createHttpResponse(httpRequest);
		System.out.println(httpResponse);
	}

	private static HttpResponse createHttpResponse(HttpRequest httpRequest) {
		HttpResponse httpResponse = new HttpResponse();
		httpResponse.setHeaders(getResponseHeaders(httpRequest.getHeaders()));
		httpResponse.setHttpType(HTTP1);
		setHttpResponseHeader(httpResponse, httpRequest);
		httpResponse.setBodyParameters(httpRequest.getBodyParameters());
		httpResponse.setMethod(httpRequest.getMethod());
		
		return httpResponse;
	}

	private static void setHttpResponseHeader(HttpResponse httpResponse, HttpRequest httpRequest) {
		if (httpRequest.getMethod().equals("GET")) {
			if (!validUrls.contains(httpRequest.getUrl())) {
				setNotFound(httpResponse);
				httpResponse.setBody(NOT_FOUND);
			} else if (httpRequest.getHeaders().get(AUTHORIZATION) == null) {
				setUnathorized(httpResponse);
				httpResponse.setBody(UNAUTHORIZED);
			} else {
				setOk(httpResponse);
				httpResponse.setBody(String.format("Greetings %s!", decodeName(getAuthorizationName(httpRequest))));
			}
		} else if (httpRequest.getMethod().equals("POST")) {
			if (!validUrls.contains(httpRequest.getUrl())) {
				setNotFound(httpResponse);
				httpResponse.setBody(NOT_FOUND);
			} else if (httpRequest.getHeaders().get(AUTHORIZATION) == null) {
				setUnathorized(httpResponse);
				httpResponse.setBody(UNAUTHORIZED);
			} else if (httpRequest.getBodyParameters().isEmpty()) {
				setBadRequest(httpResponse);
				httpResponse.setBody(BAD_REQUEST);
			} else {
				setOk(httpResponse);
				httpResponse.setBody(String.format("Greetings %s!", decodeName(getAuthorizationName(httpRequest))));
			}
		}
	}

	private static void setOk(HttpResponse httpResponse) {
		httpResponse.setStatusCode(HTTP_STATUS_CODE_OK);
		httpResponse.setStatusMessage(HTTP_OK);
	}

	private static void setBadRequest(HttpResponse httpResponse) {
		httpResponse.setStatusCode(HTTP_STATUS_CODE_BAD_REQUEST);
		httpResponse.setStatusMessage(HTTP_BAD_REQUEST);
	}

	private static void setUnathorized(HttpResponse httpResponse) {
		httpResponse.setStatusCode(HTTP_STATUS_CODE_UNAUTHORIZED);
		httpResponse.setStatusMessage(HTTP_UNAUTHORIZED);
	}

	private static void setNotFound(HttpResponse httpResponse) {
		httpResponse.setStatusCode(HTTP_STATUS_CODE_NOT_FOUND);
		httpResponse.setStatusMessage(HTTP_NOT_FOUND);
	}

	private static Map<String, String> getResponseHeaders(Map<String, String> requestHeaders) {
		Map<String, String> responseHeaders = new LinkedHashMap<>();
		if (requestHeaders.get(DATE) != null) {
			responseHeaders.put(DATE, requestHeaders.get(DATE));
		}

		if (requestHeaders.get(HOST) != null) {
			responseHeaders.put(HOST, requestHeaders.get(HOST));
		}

		if (requestHeaders.get(CONTENT_TYPE) != null) {
			responseHeaders.put(CONTENT_TYPE, requestHeaders.get(CONTENT_TYPE));
		}

		return responseHeaders;
	}

	private static HttpRequest parseRequest(String httpRequestString) {
		String[] requestLineHeadersBody = httpRequestString.split(System.lineSeparator() + System.lineSeparator());
		String[] requsetLineHeaders = requestLineHeadersBody[0].split(System.lineSeparator());
		String requestLine = requsetLineHeaders[0];
		String headersString = String.join(System.lineSeparator(),
				Arrays.asList(requsetLineHeaders).stream().skip(1).toArray(String[]::new));

		String body = null;
		if (requestLineHeadersBody.length == 2) {
			body = requestLineHeadersBody[1];
		}

		String[] requestParameters = requestLine.split("\\s+");
		String method = requestParameters[0];
		String url = requestParameters[1];
		String httpType = requestParameters[2];
		Map<String, String> headers = parseHeaders(headersString);
		Map<String, String> bodyParameters = parseBodyParameters(body);

		return createHttpRequest(method, url, httpType, headers, bodyParameters);
	}

	private static HttpRequest createHttpRequest(String method, String url, String httpType,
			Map<String, String> headers, Map<String, String> bodyParameters) {
		return new HttpRequest.Builder().method(method).url(url).httpType(httpType).headers(headers)
				.bodyParameters(bodyParameters).build();
	}

	private static Map<String, String> parseBodyParameters(String bodyString) {
		if (bodyString == null || "".equals(bodyString.trim()) || !bodyString.contains("&")) {
			return Collections.emptyMap();
		}
		Map<String, String> bodyParameters = new LinkedHashMap<>();
		String[] bodyArr = bodyString.split("&");
		for (String bodyKeyValue : bodyArr) {
			String[] keyValue = bodyKeyValue.split("=");
			String key = keyValue[0];
			String value = keyValue[1];
			bodyParameters.put(key, value);
		}

		return bodyParameters;
	}

	private static Map<String, String> parseHeaders(String headersString) {
		Map<String, String> headers = new LinkedHashMap<>();
		String[] headersArr = headersString.split(System.lineSeparator());
		for (String headerKeyValue : headersArr) {
			String[] keyValue = headerKeyValue.split(":\\s+");
			String key = keyValue[0];
			String value = keyValue[1];
			headers.put(key, value);
		}

		return headers;
	}

	private static void readUrls() throws IOException {
		String[] urls = bufferedReader.readLine().split("\\s+");
		validUrls.addAll(Arrays.asList(urls));
	}

	private static String readRequest() throws IOException {
		StringBuilder sb = new StringBuilder();
		String line = bufferedReader.readLine();

		while (line != null && line.length() > 0) {
			sb.append(line).append(System.lineSeparator());
			line = bufferedReader.readLine();
		}

		sb.append(System.lineSeparator()).append(bufferedReader.readLine());

		return sb.toString();
	}

	private static String decodeName(String name) {
		Decoder decoder = Base64.getDecoder();

		return new String(decoder.decode(name));
	}

	private static String getAuthorizationName(HttpRequest httpRequest) {
		String authorization = httpRequest.getHeaders().get(AUTHORIZATION);

		return authorization.split("\\s+")[1];
	}

	public static class HttpResponse {

		private String httpType;

		private int statusCode;

		private String statusMessage;

		private Map<String, String> headers;

		private Map<String, String> bodyParameters;

		private String body;

		private String method;
		
		public HttpResponse() {

		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public void setHttpType(String httpType) {
			this.httpType = httpType;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

		public void setStatusMessage(String statusMessage) {
			this.statusMessage = statusMessage;
		}

		public void setHeaders(Map<String, String> headers) {
			this.headers = headers;
		}

		public void setBodyParameters(Map<String, String> bodyParameters) {
			this.bodyParameters = bodyParameters;
		}

		public String getHttpType() {
			return httpType;
		}

		public int getStatusCode() {
			return statusCode;
		}

		public String getStatusMessage() {
			return statusMessage;
		}

		public Map<String, String> getHeaders() {
			return Collections.unmodifiableMap(headers);
		}

		public Map<String, String> getBodyParameters() {
			return bodyParameters;
		}
		
		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		private String getResolvedBodyParameters() {
			bodyParameters.forEach((key, value) -> {
			});
			List<Map.Entry<String, String>> parameters = new ArrayList<>();
			for (Map.Entry<String, String> bodyParameter : bodyParameters.entrySet()) {
				parameters.add(bodyParameter);
			}
			
			return String.format("%s with %s - %s, %s - %s", parameters.get(0).getValue(), parameters.get(1).getKey(),
					parameters.get(1).getValue(), parameters.get(2).getKey(), parameters.get(2).getValue());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("%s %d %s", httpType, statusCode, statusMessage)).append(System.lineSeparator());
			headers.forEach((key, value) -> {
				sb.append(String.format("%s: %s", key, value)).append(System.lineSeparator());
			});
			sb.append(System.lineSeparator());
			sb.append(body);
			if (!bodyParameters.isEmpty() && statusCode == HTTP_STATUS_CODE_OK && method.equals("POST")) {
				sb.append(String.format(" You have successfully created %s.", getResolvedBodyParameters()));
			}

			return sb.toString();
		}

		public static class Builder {

			private HttpResponse httpResponse;

			public Builder() {
				httpResponse = new HttpResponse();
			}

			public Builder httpType(String httpType) {
				httpResponse.httpType = httpType;
				return this;
			}

			public Builder statusCode(int statusCode) {
				httpResponse.statusCode = statusCode;
				return this;
			}

			public Builder headers(Map<String, String> headers) {
				httpResponse.headers = headers;
				return this;
			}

			public Builder bodyParameters(Map<String, String> bodyParameters) {
				httpResponse.bodyParameters = bodyParameters;
				return this;
			}

			public Builder body(String body) {
				httpResponse.body = body;
				return this;
			}
			
			public Builder method(String method) {
				httpResponse.method = method;
				return this;
			}

			public HttpResponse build() {
				return httpResponse;
			}
		}

	}

	public static class HttpRequest {

		private String method;

		private String url;

		private String httpType;

		private Map<String, String> headers;

		private Map<String, String> bodyParameters;

		private HttpRequest() {

		}

		public String getMethod() {
			return method;
		}

		public String getUrl() {
			return url;
		}

		public String getHttpType() {
			return httpType;
		}

		public Map<String, String> getHeaders() {
			return Collections.unmodifiableMap(headers);
		}

		public Map<String, String> getBodyParameters() {
			return Collections.unmodifiableMap(bodyParameters);
		}

		public static class Builder {

			private HttpRequest httpRequest;

			public Builder() {
				httpRequest = new HttpRequest();
			}

			public Builder method(String method) {
				httpRequest.method = method;
				return this;
			}

			public Builder url(String url) {
				httpRequest.url = url;
				return this;
			}

			public Builder httpType(String httpType) {
				httpRequest.httpType = httpType;
				return this;
			}

			public Builder headers(Map<String, String> headers) {
				httpRequest.headers = headers;
				return this;
			}

			public Builder bodyParameters(Map<String, String> bodyParameters) {
				httpRequest.bodyParameters = bodyParameters;
				return this;
			}

			public HttpRequest build() {
				return httpRequest;
			}
		}
	}
}
